describe('test mock Form ', function(){
    var formHTML;
    var collaborateur;

    beforeEach(function(){
        formHTML = new FormHTML;
        collaborateur = {};
        formHTML.createInputHTML({
            name: 'firstName',
            required: true,
            'ng-pattern': new RegExp("^[A-Za-z]+$"),
            model: collaborateur
        });
        formHTML.createInputHTML({
            name: 'lastName',
            required: true,
            initialValue: 'Lecomte',
            'ng-pattern': new RegExp("^[A-Za-z]+$"),
            model: collaborateur,
            'ng-minlength': 3
        });
        formHTML.createInputHTML({
            name: 'age',
            type: 'number',
            model: collaborateur,
            min : 18,
            required: true
        });
    });

    it('Saisi Collaborateur valide (avec lastName déjà donné)', function(){
        formHTML.inputs['firstName'].setValue("Thomas");
        expect(collaborateur.lastName).toBe('Lecomte');
        formHTML.inputs['age'].setValue(23);
        expect(formHTML.form.$invalid).toBeFalsy();
        expect(formHTML.form.$valid).toBeTruthy();
        expect(formHTML.form.$error).toEqual({});
    });

    it('Saisi Collaborateur incomplete', function(){
        formHTML.inputs['firstName'].setValue("");
        expect(formHTML.form.firstName.$invalid).toBeTruthy();
        expect(formHTML.form.firstName.$valid).toBeFalsy();
        expect(formHTML.form.firstName.$error).toEqual({required: true});
        formHTML.inputs['lastName'].setValue("Lecomte");
        expect(formHTML.form.lastName.$invalid).toBeFalsy();
        expect(formHTML.form.lastName.$valid).toBeTruthy();
        formHTML.inputs['age'].setValue();
        expect(formHTML.form.age.$invalid).toBeTruthy();
        expect(formHTML.form.age.$valid).toBeFalsy();
        expect(formHTML.form.age.$error).toEqual({required: true});

        expect(formHTML.form.$invalid).toBeTruthy();
        expect(formHTML.form.$valid).toBeFalsy();
        expect(formHTML.form.$error.required).toEqual([{}, {}]);
    });

    it('Saisi Collaborateur incorrect', function(){
        formHTML.inputs['firstName'].setValue("#Thomas");
        expect(formHTML.form.firstName.$invalid).toBeTruthy();
        expect(formHTML.form.firstName.$valid).toBeFalsy();
        expect(formHTML.form.firstName.$error).toEqual({pattern: true});
        formHTML.inputs['lastName'].setValue("7@");
        expect(formHTML.form.lastName.$invalid).toBeTruthy();
        expect(formHTML.form.lastName.$valid).toBeFalsy();
        expect(formHTML.form.lastName.$error).toEqual({pattern: true, minlength: true});
        formHTML.inputs['age'].setValue(5);
        expect(formHTML.form.age.$invalid).toBeTruthy();
        expect(formHTML.form.age.$valid).toBeFalsy();
        expect(formHTML.form.age.$error).toEqual({min: true});

        expect(formHTML.form.$invalid).toBeTruthy();
        expect(formHTML.form.$valid).toBeFalsy();
        expect(formHTML.form.$error.pattern).toEqual([{}, {}]);
        expect(formHTML.form.$error.min).toEqual([{}]);
        expect(formHTML.form.$error.minlength).toEqual([{}]);
    });
});