angular.module('FederatorApp.filters', []).filter('mailTrim', function() {
    return function(input,source) {
        if(source === "Mail:PL" || source === "Mail:BC" || source === "Mail:CC" || source === "Mail:PC"){
            var index = input.lastIndexOf("]");
            if (index > 0) {
                input = input.slice(index + 1, input.length);
            }
        }
        return input;
    }
})

.filter('trustUrl', function($sce) {
     return function(url) {
        return $sce.trustAsResourceUrl(url);
     };
 })



.filter('highlight', function($sce) {
    return function(text, phrase) {
        if (phrase && text){
            var res = phrase.split(" ");
            var arrayLength = res.length;
            for(var i =0; i < arrayLength; i++){

                text = text.replace(new RegExp('(' + res[i] + ')', 'gi'), '<span class="highlighted">$1</span>')
            }

        }

        return $sce.trustAsHtml(text);
    }
});

