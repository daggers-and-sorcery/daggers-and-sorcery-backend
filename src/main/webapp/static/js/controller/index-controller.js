'use strict';

module.exports = function ($scope, $http, $sce, newslist) {
     for (var i = 0; i < newslist.data.length; i++) {
         newslist.data[i].message = $sce.trustAsHtml(newslist.data[i].message);
         newslist.data[i].title = $sce.trustAsHtml(newslist.data[i].title);
     }

     $scope.newslist = newslist.data;
};