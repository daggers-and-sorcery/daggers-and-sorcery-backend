var RegistrationPage = require('../pages/registration-page.js');

describe('registration page tests', function() {
    var page;

    beforeEach(function () {
        page = new RegistrationPage();
    });

    it('test registration show valid on valid data', function() {
        page.emailInput.sendKeys('test@example.com');
        page.usernameInput.sendKeys('testuser');
        page.firstPasswordInput.sendKeys('topsecret');
        page.secondPasswordInput.sendKeys('topsecret');

        expect(page.registrationForm.getAttribute('class')).toMatch('ng-valid');
    });
});