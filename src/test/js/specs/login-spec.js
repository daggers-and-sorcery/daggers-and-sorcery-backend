var LoginPage = require('../pages/login-page.js');
var MenuPage = require('../pages/menu-page.js');

describe('login page tests', function() {
    var page;

    beforeEach(function () {
        page = new LoginPage();
    });

    it('test bad login', function() {
        page.usernameInput.sendKeys("invaliduser");
        page.passwordInput.sendKeys("invalidpass");

        page.submitButton.click();

        expect(page.errorAlert.isDisplayed()).toBeTruthy();
    });

    it('test good login', function() {
        page.login("dbuser", "topsecret");

        var menu = new MenuPage();

        expect(menu.menuPanel.isDisplayed()).toBeTruthy();

        page.logout();
    });
});