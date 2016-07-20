function Input(){
    this.$invalid = false;
    this.$valid = true;
    this.$error = {};
}

function InputHTML() {
    var inputHTML = this;
    inputHTML.name = "";
    inputHTML.type = 'text';
    inputHTML['ng-pattern'] = null;
    inputHTML['ng-focus'] = null;
    inputHTML['ng-change'] = null;
    inputHTML['ng-blur'] = null;
    inputHTML.required = false;
    inputHTML.model = null;
    inputHTML.initialValue = "";
    inputHTML.input = new Input;
    var input = inputHTML.input;

    inputHTML.setProperties = function(obj){
        for (var property in obj) {
            if (inputHTML[property] !== undefined) {
                inputHTML[property] = obj[property];
            }
        }
        if(inputHTML.name && inputHTML.model && inputHTML.initialValue) {
            inputHTML.model[inputHTML.name] = inputHTML.initialValue;
        }
    };

    inputHTML.actualiseStatesInput = function(){
       //inputHTML.model
       //inputHTML.required
    };
}

function Form(){
    var form = this;
    form.$error = {};
    form.$invalid = false;
    form.$valid = true;
}

function FormHTML() {
    var formHTML = this;
    formHTML.inputs = [];
    formHTML.inputsMessages = [];
    formHTML.form = new Form;
    var form = formHTML.form;

    formHTML.actualiseStatesForm = function(){
        form.$error = {};
        form.$invalid = false;
        form.$valid = true;
        formHTML.inputs.forEach(function(inputHTML){
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

    formHTML.createInputHTML = function(obj) {
        var inputHTML = new InputHTML;
        if(!obj.name) throw {message: "property 'name' of InputHTML is not defined"};
        if(!obj.model) throw {message: "property 'model' of InputHTML '"+obj.name+"' is not defined"};
        inputHTML.setProperties(obj);
        formHTML.inputs[inputHTML.name] = inputHTML;
        formHTML.form[inputHTML.name] = inputHTML.input;

        inputHTML.setValue = function(value){
            inputHTML.model[inputHTML.name] = value;
            //build errors
            //change states of messages
            formHTML.actualiseStatesForm();
        };
    };
}
