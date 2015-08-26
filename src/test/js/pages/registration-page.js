var RegistrationPage = function () {
    browser.get('/');

    element(by.buttonText('Create an account')).click();
};

RegistrationPage.prototype = Object.create({}, {
        registrationForm: {
            get: function () {
                return element(by.css('form[name="registration_form"]'));
            }
        },
        emailInput: {
            get: function () {
                return this.registrationForm.element(by.model('user.email'));
            }
        },
        emailErrorMessages: {
            get: function () {
                return this.registrationForm.all(by.css('div[ng-messages="registration_form.email.$error"] > div'));
            }
        },
        usernameInput: {
            get: function () {
                return this.registrationForm.element(by.model('user.username'));
            }
        },
        firstPasswordInput: {
            get: function () {
                return this.registrationForm.element(by.model('user.passwordFirst'));
            }
        },
        secondPasswordInput: {
            get: function () {
                return this.registrationForm.element(by.model('user.passwordSecond'));
            }
        },
        submit: {
            get: function () {
                return this.registrationForm.element(by.buttonText('Register'));
            }
        }
    }
);

module.exports = RegistrationPage;