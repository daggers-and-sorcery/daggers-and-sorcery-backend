var LoginPage = function () {
    browser.get('/');
};

LoginPage.prototype = Object.create({}, {
        loginPanel: {
            get: function () {
                return element(by.css('.login-panel'));
            }
        },
        usernameInput: {
            get: function () {
                return this.loginPanel.element(by.model('user.username'));
            }
        },
        passwordInput: {
            get: function () {
                return this.loginPanel.element(by.model('user.password'));
            }
        },
        submitButton: {
            get: function () {
                return this.loginPanel.element(by.buttonText('Submit'));
            }
        },
        errorAlert: {
            get: function () {
                return this.loginPanel.element(by.css('div.alert'));
            }
        },
        login: {
            value: function () {
                this.usernameInput.sendKeys("dbuser");
                this.passwordInput.sendKeys("topsecret");

                this.submitButton.click();
            }
        },
        logout: {
            value: function () {
                element(by.buttonText('Logout')).click();
            }
        }
    }
);

module.exports = LoginPage;