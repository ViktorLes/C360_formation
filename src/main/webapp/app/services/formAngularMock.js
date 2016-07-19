function Input() {
    this.type = 'text';
    this['ng-pattern'] = null;
    this['ng-focus'] = null;
    this['ng-change'] = null;
    this['ng-blur'] = null;
    this.required = false;
    this.model = null;
    this.initialValue = "";
    this.$invalid = false;
    this.$valid = true;
    this.$error = {};
}

function Form() {
    this.$error = {};
    this.$invalid = false;
    this.$valid = true;
    this.inputs = [];
    this.inputsMessages = [];
    this.createInput = function (name, obj) {
        var input = new Input;
        for (var property in obj) {
            if (input[property] !== undefined) {
                input[property] = obj[property];
            }
        }
        input.setValue = function(){

        };
        this.inputs[name] = input;
    };
}
