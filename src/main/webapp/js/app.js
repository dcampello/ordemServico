/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module("ordemServico", ['ngFileUpload']);
        app.value('urlBase','http://localhost:8080/ordemServico/rest/');
        app.controller("ordemServicoCtrl", function ($http, urlBase, ImageService,$scope){
            var self = this;
            
            self.ordensServico = [];
            self.ordemServico = undefined;
            
            
            self.atendimento = undefined;
            
            self.novo = function() {
                self.ordemServico = {};
            };
            
            self.salvar = function() {
                var metodo = 'POST';
                if (self.ordemServico.id) {
                    metodo = 'PUT';
                }

                $http({
                    method: metodo,
                    url: urlBase + 'ordensServico/',
                    data: self.ordemServico
                }).then(function sucessCallback(response) {
                    self.atualizarTabela();
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });
            };
            
            self.alterar = function(ordemServico) {
                self.ordemServico = ordemServico;
                self.atendimento = true;
                ImageService.sendData(self.ordemServico);
            };
            
            self.deletar = function(ordemServico) {
                self.ordemServico = ordemServico;
                
                $http({
                    method: 'DELETE',
                    url: urlBase + 'ordensServico/' + self.ordemServico.id + '/'
                }).then(function sucessCallback(response) {
                    self.atualizarTabela();
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });
            };
            
            self.concluir = function(ordemServico) {
                self.ordemServico = ordemServico;
             
                $http({
                    method: 'PUT',
                    url: urlBase + 'ordensServico/' + self.ordemServico.id + '/'
                }).then(function sucessCallback(response) {
                    self.atualizarTabela();
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });
            
            };
            
             self.ocorreuErro = function() {
                alert("Ocorreu um erro inesperado!");
            };
            
            self.atualizarTabela = function() {
                $http({
                    method: 'GET',
                    url: urlBase + 'ordensServico/'
                }).then(function sucessCallback(response) {
                    self.ordensServico = response.data;
                    self.ordemServico = undefined;
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });
            };
            
            self.activate = function (){
                self.atualizarTabela();
            };
            
            self.activate();
            
});
        
app.controller('MyCtrl', ['$scope', 'Upload', '$timeout', 'ImageService', 'urlBase' , function ($scope, Upload, $timeout, ImageService, urlBase ) {

    $scope.uploadFiles = function(files, errFiles) {
        $scope.files = files;
        $scope.errFiles = errFiles;
        $scope.ordemServico = ImageService.getData();
        console.log($scope.ordemServico);    
        
        angular.forEach(files, function(file) {

            file.upload = Upload.upload({
//                url: urlBase + 'ordensServico/upload',
//                data: {file: file, ordemServico:$scope.ordemServico}
            });
            
            file.upload.then(function (response) {
                $timeout(function () {
                    file.result = response.data;
                    
                });
                
            }, function (response) {
                if (response.status > 0)
                    $scope.errorMsg = response.status + ': ' + response.data;
            }, function (evt) {
                file.progress = Math.min(100, parseInt(100.0 * 
                                         evt.loaded / evt.total));
            });
            
            
        });
    };
}]);

app.service('ImageService', function(){
   var arquivosCarregados = {};
   return{
       sendData: function(data){
           arquivosCarregados = data;
       },
       getData: function(){
           return arquivosCarregados;
       }
   }
});
