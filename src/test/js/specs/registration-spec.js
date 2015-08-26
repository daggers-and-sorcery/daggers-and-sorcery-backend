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

    it('test registration with email invalid data', function() {
        page.emailInput.sendKeys('bademail');

        page.submit.click();

        expect(page.emailErrorMessages.count()).toEqual(1);
        expect(page.emailErrorMessages.get(0).getText()).toEqual("The email must be a valid email address.");
    });

    it('test registration with username invalid data', function() {
        page.usernameInput.sendKeys('small');

        page.submit.click();

        expect(page.usernameErrorMessages.count()).toEqual(1);
        expect(page.usernameErrorMessages.get(0).getText()).toEqual("The minimum username length is 6 characters.");

        page.usernameInput.clear();
        page.usernameInput.sendKeys('!thisistoolongihopeatleast');

        page.submit.click();

        expect(page.usernameErrorMessages.count()).toEqual(2);
        expect(page.usernameErrorMessages.get(0).getText()).toEqual("The maximum username length is 16 characters.");
        expect(page.usernameErrorMessages.get(1).getText()).toEqual("The username can only contain letters (a-z, A-Z) or numbers.");
    });
});