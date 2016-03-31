describe('products category service', function () {
  it('should return the expected categories', function () {
    angular.mock.module('products');

    var service;
    
    // Get the service from the injector
    angular.mock.inject(function GetDependencies(CategoryService) {
      service = CategoryService;
    });

    // call the function on our service instance
    var categories = service.getCategories();

    expect(categories).toEqual({ 1: 'Beverages', 2: 'Condiments' });
  });
});