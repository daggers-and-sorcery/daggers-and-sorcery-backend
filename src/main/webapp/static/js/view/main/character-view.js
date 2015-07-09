'use strict';

module.exports = {
    templateUrl: '/partial/main/character.html',
    resolve: {
        characterData: function ($http) {
            return $http.get('/character/info').then(function(response) {
                return formatData(response.data);
            });
        }
    },
    controller: function ($scope, $http, ATTRIBUTE_BONUS_MAP, characterData) {
        $scope.attributePopoverUrl = '/partial/popover/attribute.html';
        $scope.user = characterData;
        $scope.attributeBonusNameMap = ATTRIBUTE_BONUS_MAP;
    }
};

function formatData(response) {
    var structuredAttributes = {
        'GENERAL_PHYSICAL': {},
        'GENERAL_MENTAL': {}
    };

    angular.forEach(response.data.attribute, function(value, key) {
        if (value.attribute.attributeType === 'GENERAL') {
            if(value.attribute.generalAttributeType === 'PHYSICAL') {
                structuredAttributes['GENERAL_PHYSICAL'][value.attribute.name] = value;
            } else {
                structuredAttributes['GENERAL_MENTAL'][value.attribute.name] = value;
            }
        } else {
            if(structuredAttributes[value.attribute.attributeType] === undefined) {
                structuredAttributes[value.attribute.attributeType] = {};
            }

            structuredAttributes[value.attribute.attributeType][value.attribute.name] = value;
        }
    });

    response.data.attribute = structuredAttributes;

    return response.data;
}