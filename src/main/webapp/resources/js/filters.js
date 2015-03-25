angular.module('FederatorApp.filters', []).filter('mailTrim', function() {
    return function(input,source) {
        if(source == "Mail" ){
            var index = input.lastIndexOf("]");
            if (index > 0) {
                input = input.slice(index + 1, input.length);
            }
        }
        console.log(source);
        return input;
    };
});
