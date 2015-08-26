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

    it('test registration with all invalid data', function() {
        page.emailInput.sendKeys('bademail');

        page.submit.click();

        expect(page.emailErrorMessages.count()).toEqual(1);
        expect(page.emailErrorMessages.get(0).getText()).toEqual("The email must be a valid email address.");
    });
});