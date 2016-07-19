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
    var form = this;
    form.$error = {};
    form.$invalid = false;
    form.$valid = true;
    form.inputs = [];
    form.inputsMessages = [];
    form.createInput = function (name, obj) {
        var input = new Input;
        for (var property in obj) {
            if (input[property] !== undefined) {
                input[property] = obj[property];
            }
        }
        input.setValue = function(value){
            input.model[name] = value;
            //build errors
            //change states of messages
            form.actualiseForm();
        };
        form.inputs[name] = input;
    };
    form.actualiseForm = function(){
        //passer en revue l'array d'inputs
    }
}
