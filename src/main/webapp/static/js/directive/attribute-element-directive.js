'use strict';

module.exports = require('js/app.js').directive('attributeElement', function () {
    return {
        restrict: 'A',
        scope: {
            attributeValue: '=attributeValue',
            attributeName: '=attributeName'
        },
        controller: function ($scope, ATTRIBUTE_BONUS_MAP) {
            $scope.attributePopover = {
                templateUrl: '/partial/popover/attribute.html',
            };

            $scope.attributeBonusNameMap = ATTRIBUTE_BONUS_MAP;
        },
        template: require('partial/directive/attribute-element.html')
    }
});