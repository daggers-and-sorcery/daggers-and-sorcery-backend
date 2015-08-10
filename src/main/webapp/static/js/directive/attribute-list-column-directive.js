'use strict';

module.exports = require('js/app.js').directive('attributeListColumn', function () {
    return {
        restrict: 'E',
        scope: {
            columnAlignId: '=columnAlignId',
            columnAlign: '@columnAlign',
            attributes: '=source'
        },
        controller: function ($scope, ATTRIBUTE_BONUS_MAP) {
            $scope.attributePopover = {
                templateUrl: '/partial/popover/attribute.html',
            };

            $scope.attributeBonusNameMap = ATTRIBUTE_BONUS_MAP;
        },
        template: require('partial/directive/attribute-list-column.html')
    };
});