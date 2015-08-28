var MenuPage = function () {
};

MenuPage.prototype = Object.create({}, {
        menuPanel: {
            get: function () {
                return element(by.css('.menu-panel'));
            }
        },
    }
);

module.exports = MenuPage;