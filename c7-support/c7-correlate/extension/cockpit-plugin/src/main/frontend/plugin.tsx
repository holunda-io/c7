import React from "react";
import CorrelateMessagesView from "./correlate-messages-view";
import {Plugin} from './lib/camunda-plugin';
import {CookiesProvider} from "react-cookie";
import {createRoot, Root} from "react-dom/client";

let container: Root;

const correlateView: Plugin = {
  id: 'correlate-cockpit-plugin-route',
  pluginPoint: 'cockpit.route',
  priority: 4,
  properties: {
    path: '/correlation'
  },
  render: (node: HTMLElement, { api }) => {
    container = createRoot(node);
    const urlPrefix = `${api.cockpitApi}/plugin/correlate-cockpit-plugin/${api.engine}`;
    container.render(
      <CookiesProvider>
      <CorrelateMessagesView camundaRestPrefix={urlPrefix} />
      </CookiesProvider>
    );
  },
  unmount: () => {
    container.unmount();
  }
};

const navigation: Plugin = {
  id: 'correlate-cockpit-plugin-navigation',
  pluginPoint: 'cockpit.navigation',
  priority: 4,
  properties: {
    path: '/correlation'
  },
  render: (container) => {
    container.innerHTML = '<a href="#/correlation">Correlation</a>';
  }
};

// TODO: add metric reporting eventually
/*
const metrics: Plugin = {
  id: 'correlate-cockpit-plugin-metrics',
  pluginPoint: 'cockpit.metrics',
  priority: 4,
  render: (container) => {
    container.innerHTML = '<div>Metrics</div>';
  }
};
*/

export default [correlateView, navigation];
