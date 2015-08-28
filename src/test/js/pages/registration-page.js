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
        registrationPanel: {
            get: function () {
                return element(by.css('div.content-middle > div.panel'));
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
        usernameErrorMessages: {
            get: function () {
                return this.registrationForm.all(by.css('div[ng-messages="registration_form.username.$error"] > div'));
            }
        },
        firstPasswordInput: {
            get: function () {
                return this.registrationForm.element(by.model('user.passwordFirst'));
            }
        },
        firstPasswordErrorMessages: {
            get: function () {
                return this.registrationForm.all(by.css('div[ng-messages="registration_form.passwordFirst.$error"] > div'));
            }
        },
        secondPasswordInput: {
            get: function () {
                return this.registrationForm.element(by.model('user.passwordSecond'));
            }
        },
        secondPasswordErrorMessages: {
            get: function () {
                return this.registrationForm.all(by.css('div[ng-messages="registration_form.passwordSecond.$error"] > div'));
            }
        },
        submit: {
            get: function () {
                return this.registrationForm.element(by.buttonText('Register'));
            }
        },
        successAlert: {
            get: function () {
                 return this.registrationPanel.element(by.css('div[ng-show="successfulRegistration"]'));
            }
        },
        failAlertHolder: {
            get: function () {
                 return this.registrationPanel.element(by.css('div[ng-show="errorList.length > 0"]'));
            }
        },
        failAlertById: {
            value: function (id) {
                 return this.failAlertHolder.all(by.repeater('error in errorList')).get(id);
            }
        }
    }
);

module.exports = RegistrationPage;