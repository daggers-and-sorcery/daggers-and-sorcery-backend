'use strict';

module.exports = require('app.js').directive('attributeListColumn', function () {
    return {
        restrict: 'E',
        scope: {
            columnAlignId: '=columnAlignId',
            columnAlign: '@columnAlign',
            attributes: '=source'
        },
        controller: function ($scope) {
            $scope.attributeNameMap = {
                'MOVEMENT': 'Movement Points',

                'LIFE': 'Life Points',
                'MANA': 'Mana Points',
                'INITIATION': 'Initiation',
                'ATTACK': 'Attack',
                'AIMING': 'Aiming',
                'DEFENSE': 'Defense',
                'SPELL_RESISTANCE': 'Spell resistance',
                'DAMAGE': 'Damage',
                'RANGED_DAMAGE': 'Ranged Damage',

                'STRENGTH': 'Strength',
                'PERCEPTION': 'Perception',
                'DEXTERITY': 'Dexterity',
                'SWIFTNESS': 'Swiftness',
                'VITALITY': 'Vitality',
                'ENDURANCE': 'Endurance',
                'BEAUTY': 'Beauty',

                'INTELLIGENCE': 'Intelligence',
                'WISDOM': 'Wisdom',
                'WILLPOWER': 'Willpower',
                'CHARISMA': 'Charisma'
            };

            $scope.attributePopover = {
                templateUrl: '/partial/popover/attribute.html',
            };

            $scope.attributeBonusNameMap = {
                'INITIAL': 'Initial value',
                'RACIAL': 'Racial bonus',
                'SKILL': 'Skill bonus',
                'LEVEL': 'Skill level',
                'GENERAL_ATTRIBUTE': 'General attribute bonus'
            };
        },
        templateUrl: '/directive/attribute-list-column.html'
    };
});