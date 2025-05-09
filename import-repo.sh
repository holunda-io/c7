import_repo_into_monorepo() {

  local old_repo_name="$1"          # e.g., my-old-lib
  local old_repo_url="$2"           # e.g., https://github.com/my-org/my-old-lib.git
  local monorepo_path="$(pwd)"      # assumes you're running this from the monorepo root
  local tmp_dir=$(mktemp -d)

  echo "==> Importing $old_repo_name from $old_repo_url into $target_subdir"

  # 1. Clone the old repo
  git clone "$old_repo_url" "$tmp_dir"
  cd "$tmp_dir" || exit 1

  # 2. Rewrite history to move contents into a subdir
  git filter-repo --to-subdirectory-filter "_import/$old_repo_name"

  # 3. Add the monorepo as a remote and fetch
  git remote add monorepo "$monorepo_path"
  git fetch monorepo

  # 4. Create import branch and merge develop
  git checkout -b import-$old_repo_name
  git merge monorepo/develop --allow-unrelated-histories --no-edit

  # 5. Push import branch to monorepo
  git push monorepo import-$old_repo_name

  # 6. Merge into develop inside the monorepo
  cd "$monorepo_path" || exit 1
  git fetch .git/modules/"$old_repo_name" || true
  git fetch origin
  git checkout develop
  git merge import-$old_repo_name

  echo "==> Done importing $old_repo_name into $target_subdir"
}
