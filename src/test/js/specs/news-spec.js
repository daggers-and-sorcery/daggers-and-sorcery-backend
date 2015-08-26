var NewsPage = require('../pages/news-page.js');

describe('news page tests', function() {
    var page;

    beforeEach(function () {
        page = new NewsPage();
    });

    it('test news list print', function() {
        expect(page.newsList.count()).toEqual(2);

        expect(page.newsTitleAt(0).getText()).toEqual("Some more tests");
        expect(page.newsDateAt(0).getText()).toEqual("18. Mar. 2005.");
        expect(page.newsMessageAt(0).getText()).toEqual("ZZzz hello world...");
        expect(page.newsDividerAt(0).isPresent()).toEqual(true);

        expect(page.newsTitleAt(1).getText()).toEqual("This is some test title");
        expect(page.newsDateAt(1).getText()).toEqual("16. Jan. 1977.");
        expect(page.newsMessageAt(1).getText()).toEqual("Lorem ipsum dolor sit ameth....");
        expect(page.newsDividerAt(1).isPresent()).toEqual(false);
    });
});