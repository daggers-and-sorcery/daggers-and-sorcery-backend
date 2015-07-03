var x = 1;
window.view['main']['index'] = {
    templateUrl: "/partial/main/index.html",
    resolve: {
        newslist: function ($http) {
            return $http({method: 'GET', url: '/news/last'});
        }
    },
    controller: function ($scope, $http, $sce, newslist) {
        for (var i = 0; i < newslist.data.length; i++) {
            newslist.data[i].message = $sce.trustAsHtml(newslist.data[i].message);
            newslist.data[i].title = $sce.trustAsHtml(newslist.data[i].title);
        }

        $scope.newslist = newslist.data;
    }
};