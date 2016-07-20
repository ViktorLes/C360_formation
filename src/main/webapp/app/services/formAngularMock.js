function Input(){
    this.$invalid = false;
    this.$valid = true;
    this.$error = {};
}

function InputHTML() {
    this.input = new Input;
    this.type = 'text';
    this['ng-pattern'] = null;
    this['ng-focus'] = null;
    this['ng-change'] = null;
    this['ng-blur'] = null;
    this.required = false;
    this.model = null;
    this.initialValue = "";
}

function Form(){
    var form = this;
    form.$error = {};
    form.$invalid = false;
    form.$valid = true;
}

function FormHTML() {
    var formHTML = this;
    formHTML.form = new Form;
    var form = formHTML.form;
    formHTML.inputs = [];
    formHTML.inputsMessages = [];

    formHTML.actualiseForm = function(){
        form.$error = {};
        form.$invalid = false;
        form.$valid = true;
        formHTML.inputs.forEach(function(inputHTML, inputName){
            var input = inputHTML.input;
            if(form.$valid && input.$invalid){
                form.$invalid = true;
                form.$valid = false;
            }
            for(var error in input.$error){
                if(input.$error[error]){
                    if(!Array.isArray(form.$error[error])){
                        form.$error[error] = [];
                    }
                    form.$error[error].push({});
                }
            }
        });
    };

    formHTML.createInputHTML = function (name, obj) {
        var inputHTML = new InputHTML;
        for (var property in obj) {
            if (inputHTML[property] !== undefined) {
                inputHTML[property] = obj[property];
            }
        }
        formHTML.inputs[name] = inputHTML;
        formHTML.form[name] = inputHTML.input;

        inputHTML.setValue = function(value){
            inputHTML.model[name] = value;
            //build errors
            //change states of messages
            formHTML.actualiseForm();
        };
    };
}
