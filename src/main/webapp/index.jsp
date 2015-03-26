<!DOCTYPE html>
<html lang="en" ng-app="FederatorApp">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="msaccotelli">
    <title>Federator</title>
    <link rel='stylesheet' href='webjars/bootstrap/3.1.0/css/bootstrap.min.css'>
    <script src="webjars/angularjs/1.2.26/angular.min.js"></script>
    <script src="resources/js/app.js"></script>
    <script src="resources/js/services.js"></script>
    <script src="resources/js/directives.js"></script>
    <script src="resources/js/controllers.js"></script>
    <script src="resources/js/filters.js"></script>

    <style>
        .custom80 {
            width: 80px !important;
            margin-left: 10px;
        }

        input[type="checkbox"] {
            margin-left: 10px
        }

        .box{
            display: none;
            width: 100%;
        }

        a:hover + .box,.box:hover{
            display: block;
            position: relative;
            z-index: 100;
        }

        a.result {
            font-size: larger;
        }
    </style>
</head>

<body data-ng-controller="SearchController">
<div class="container-fluid">

    <div class="row">
        <div class="col-md-12">
            <h2>Search</h2>

            <form data-ng-submit="doParallelSearch()">

                <input type="text" data-ng-model="query" placeholder="Search" data-auto-focus>

                <button type="submit" class="btn btn-lg btn-primary" data-ng-disabled="!query || inflight || search.searchIn.length == 0">
                    Search
                </button>

                <label ng-repeat="searchLocation in searchLocations">
                    <input type="checkbox" checklist-model="search.searchIn" checklist-value="searchLocation">
                    {{searchLocation}}
                </label>
                <input class="btn btn-default custom80" type="button" ng-click="checkAll()" value="All">
                <input class="btn btn-default custom80" type="button" ng-click="uncheckAll()" value="None">

            </form>

        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <span data-ng-show="!inflight && searched && results.length == 0">No results</span>
            <i data-ng-show="inflight && results.length == 0">Please wait..</i>

            <div id="results" data-ng-show="results.length > 0">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Result ({{results.length}}) {{inflight ? 'In progress..' : ''}}</th>
                        <th>Source</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr data-ng-repeat="result in results | orderBy: ['source', 'title']">
                        <td>
                            <b><a target="_blank"
                                  href="{{result.href}}"
                                  class="result"
                                  title="{{result.title}}">{{result.title | mailTrim : result.source}}</a><div class="box"><iframe src="{{result.href | trustUrl}}" scrolling ="no" width = "500" height = "300" seamless></iframe></div></b><br/>
                            {{result.content}}
                        </td>
                        <td>{{result.source}}</td>

                    </tr>
                    </tbody>
                </table>
                <hr/>
            </div>
        </div>
    </div>
</div>

</body>
</html>
