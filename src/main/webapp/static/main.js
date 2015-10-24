// NPM Modules
var $ = require('jquery');
window.jQuery = $;
window.$ = $;

require('angular');

require('angular-messages');

require('angular-ui-router');

require('angular-ui-bootstrap');

require('angularjs-slider');
require('angularjs-slider/dist/rzslider.min.css');

require('bootstrap');
require('bootstrap/dist/css/bootstrap.min.css');

// Router
require('js/router/router.js');

// Service
require('js/service/character-data-formatter.js');

// Controllers
require('js/controller/main-controller.js');

// Directives
require('js/directive/equals-directive.js');
require('js/directive/game-map-directive.js');
require('js/directive/attribute-list-column-directive.js');
require('js/directive/attribute-element-directive.js');
require('js/directive/inventory-item-column-directive.js');
require('js/directive/spell-entry-column-directive.js');
require('js/directive/equipment-element-directive.js');
require('js/directive/svg-image-directive.js');

// Filters
require('js/filter/replace-filter.js');
require('js/filter/capitalize-filter.js');

// Constants
require('js/constant/attribute-bonus-map-constant.js');

// Interceptors
require('js/interceptor/combat-update-interceptor.js');
require('js/interceptor/combat-update-interceptor-config.js');

// CSS
require('css/custom.css');