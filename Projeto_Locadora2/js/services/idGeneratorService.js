angular.module("alugaMovie").provider("idGenerator", function() {
    this.$get = function () {
        return {
            generate: function () {
                var serialId = "";
                while(serialId.length < 5) {
                    serialId += String.fromCharCode(Math.floor(Math.random() * 64) +32);
                }
                return serialId;
            }
        }
    }
})