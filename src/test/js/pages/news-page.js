var NewsPage = function () {
    browser.get('/');
};

NewsPage.prototype = Object.create({}, {
        newsList: {
            get: function () {
                return element.all(by.css('.news-entry'));
            }
        },
        newsDateAt: {
            value: function (id) {
                return this.newsList.get(id).element(by.css('.news-subtitle'));
            }
        },
        newsTitleAt: {
            value: function (id) {
                return this.newsList.get(id).element(by.css('.news-title'));
            }
        },
        newsMessageAt: {
            value: function (id) {
                return this.newsList.get(id).element(by.css('.news-message'));
            }
        },
        newsDividerAt: {
            value: function (id) {
                return this.newsList.get(id).element(by.css('.news-divider'));
            }
        }
    }
);

module.exports = NewsPage;