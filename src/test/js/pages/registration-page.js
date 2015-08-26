var RegistrationPage = function () {
    browser.get('/');

    element(by.linkText('Create an account')).click();
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
        }
    }
);

module.exports = RegistrationPage;