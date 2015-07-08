module.exports = {
    templateUrl: '/partial/main/character.html',
    controller: function ($scope, $http, ATTRIBUTE_BONUS_MAP) {
        $scope.attributes = {
            basic: {},
            combat: {}
        };

        $scope.attributePopover = {
            templateUrl: '/partial/popover/attribute.html'
        };

        $scope.attributeBonusNameMap = ATTRIBUTE_BONUS_MAP;

        $http.get('/character/info').success(function (data, status, headers, config) {
            var structuredAttributes = {
                'GENERAL_PHYSICAL': {},
                'GENERAL_MENTAL': {}
            };

            angular.forEach(data.data.attribute, function(value, key) {
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

            data.data.attribute = structuredAttributes;

            $scope.user = data.data;
        });
    }
};