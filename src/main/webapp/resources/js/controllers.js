/**
 * Created by msaccotelli on 10/13/2014.
 */

angular.module('FederatorApp.controllers', []).
    controller('SearchController', function ($scope, SearchService) {
        $scope.results = [];

        $scope.searchLocations = [
            'Confluence',
            'Jira',
            'Mail:PL',
            'Mail:BC',
            'Mail:CC',
            'Mail:PC',
            'Intranet',
            'Wiki'
        ];

        $scope.search = {
            searchIn: angular.copy($scope.searchLocations)
        };

        $scope.checkAll = function () {
            $scope.search.searchIn = angular.copy($scope.searchLocations);
        };

        $scope.uncheckAll = function () {
            $scope.search.searchIn = [];
        };

        $scope.doParallelSearch = function () {
            $scope.inflight = 0;
            $scope.results = [];

            angular.forEach($scope.search.searchIn, function (value) {
                var searchPromise = SearchService.search(
                    {
                        query: $scope.query,
                        searchIn: [value]
                    }
                );

                $scope.inflight++;

                searchPromise.then(
                    function (payload) {
                        $scope.results = $scope.results.concat(payload.data['searchResults']);
                        $scope.inflight--;
                        $scope.searched = true;
                    }, function () {
                        // decrease even in case of error
                        $scope.inflight--;
                    });
            });
        };

        $scope.doSummary = function (result) {
            var summaryPromise = SearchService.summarize(result.source, result.href);

            summaryPromise.then(
                function (payload) {
                    //console.log(payload);
                    $scope.summary = payload.data['content'];
                });
        };

    });
