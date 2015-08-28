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

        page.submit.click();

        expect(page.registrationForm.getAttribute('class')).toMatch('ng-valid');
        expect(page.successAlert.isDisplayed()).toBeTruthy();
        expect(page.successAlert.getText()).toBe("Congratulations, your registration is successful!");
    });

    it('test registration with already registered', function() {
        page.emailInput.sendKeys('test@example.com');
        page.usernameInput.sendKeys('testuser');
        page.firstPasswordInput.sendKeys('topsecret');
        page.secondPasswordInput.sendKeys('topsecret');

        expect(page.registrationForm.getAttribute('class')).toMatch('ng-valid');

        page.submit.click();

        expect(page.failAlertHolder.isDisplayed()).toBeTruthy();
        expect(page.failAlertById(0).getText()).toBe("An user with this username already exists.");
        expect(page.failAlertById(1).getText()).toBe("An user with this email already exists.");
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

    it('test registration with first password invalid data', function() {
        page.firstPasswordInput.sendKeys('small');

        page.submit.click();

        expect(page.firstPasswordErrorMessages.count()).toEqual(1);
        expect(page.firstPasswordErrorMessages.get(0).getText()).toEqual("The minimum password length is 6 characters.");
    });

    it('test registration with second password invalid data', function() {
        //Test length
        page.secondPasswordInput.sendKeys('small');

        page.submit.click();

        expect(page.secondPasswordErrorMessages.count()).toEqual(1);
        expect(page.secondPasswordErrorMessages.get(0).getText()).toEqual("The minimum password length is 6 characters.");

        //Test equals
        page.firstPasswordInput.sendKeys('thisisgood');
        page.secondPasswordInput.sendKeys('muchlonger');

        page.submit.click();

        expect(page.secondPasswordErrorMessages.count()).toEqual(1);
        expect(page.secondPasswordErrorMessages.get(0).getText()).toEqual("The two password must be equals!");
    });

    it('test if required texts is printed', function() {
        page.submit.click();

        expect(page.emailErrorMessages.count()).toEqual(1);
        expect(page.emailErrorMessages.get(0).getText()).toEqual("The email is required.");

        expect(page.usernameErrorMessages.count()).toEqual(1);
        expect(page.usernameErrorMessages.get(0).getText()).toEqual("The username is required.");

        expect(page.firstPasswordErrorMessages.count()).toEqual(1);
        expect(page.firstPasswordErrorMessages.get(0).getText()).toEqual("The password is required.");

        expect(page.secondPasswordErrorMessages.count()).toEqual(1);
        expect(page.secondPasswordErrorMessages.get(0).getText()).toEqual("The password is required.");
    });

    it('test if race changing works', function() {
        expect(page.raceName.getText()).toEqual("Human");
        expect(page.specialHumanModifierDisplay.isDisplayed()).toBeTruthy();

        page.changeRaceLeft.click();
        expect(page.raceName.getText()).toEqual("Draconic");
        expect(page.racialModifierById(0).getText()).toBe("30% - Willpower");
        expect(page.racialModifierById(1).getText()).toBe("10% - Endurance");
        expect(page.racialModifierById(2).getText()).toBe("-30% - Dexterity");
        expect(page.racialModifierById(3).getText()).toBe("-20% - Swiftness");
        expect(page.racialModifierById(4).getText()).toBe("-10% - Perception");

        page.changeRaceRight.click();
        page.changeRaceRight.click();
        expect(page.raceName.getText()).toEqual("Orc");
        expect(page.racialModifierById(0).getText()).toBe("20% - Strength");
        expect(page.racialModifierById(1).getText()).toBe("10% - Endurance");
        expect(page.racialModifierById(2).getText()).toBe("10% - Vitality");
        expect(page.racialModifierById(3).getText()).toBe("-30% - Intelligence");
        expect(page.racialModifierById(4).getText()).toBe("-10% - Wisdom");
        expect(page.racialModifierById(5).getText()).toBe("-10% - Willpower");
    });
});