'use strict';

module.exports = require('js/app.js').factory('characterDataFormatter', function() {
    return {
        format: function(response) {
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
    }
});