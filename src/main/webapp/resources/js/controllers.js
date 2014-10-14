/**
 * Created by msaccotelli on 10/13/2014.
 */

angular.module('FederatorApp.controllers', []).
    controller('SearchController', function ($scope, SearchService) {
        $scope.results = [];

        $scope.doSearch = function () {
            $scope.inflight = true;
            $scope.results = [];

            var searchPromise = SearchService.search($scope.query);

            searchPromise.then(
                function (payload) {
                    $scope.results = payload.data['searchResults'];
                    $scope.inflight = false;
                    $scope.searched = true;
                });
        };

        $scope.doSummary = function (result) {
            var summaryPromise = SearchService.summarize(result.source, result.href);

            summaryPromise.then(
                function (payload) {
                    console.log(payload);
                    $scope.summary = payload.data['content'];
                });
        };

    });
