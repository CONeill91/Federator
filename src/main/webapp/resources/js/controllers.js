/**
 * Created by msaccotelli on 10/13/2014.
 */

angular.module('FederatorApp.controllers', []).
    controller('SearchController', function ($scope, SearchService) {
        $scope.results = [];

        $scope.searchLocations = [
            'Confluence',
            'Jira',
            'Mail:PL',//this is mail:pl
            'Mail:BC',
            'Mail:CC',
            'Mail:PC', // this is mail:pc
            'Intranet',
            'Wiki'
        ];

        $scope.activateTab = function(activeTab) {
            console.log('This is what the active tab is... %s', activeTab);
            $scope.activeTab = activeTab;
            console.log('Active Tab is now %s', $scope.activeTab);
        }

        $scope.filterResults = function(result){// this is the filter which is called in the index class and "result" is passed over from the index class
           console.log("The result.source is shown below");
           console.log(result.source);
           console.log('Active tab is %s, source is %s', $scope.activeTab, result.source);  // This is where I think my error could be... Active tab is Mail:PC, source is Mail:P
           return !$scope.activeTab || result.source === $scope.activeTab;
        }

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
