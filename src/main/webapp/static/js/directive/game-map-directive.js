'use strict';

module.exports = require('js/app.js').directive('gameMap', function ($injector) {
    return {
        template: '<div id="game-map"></div>',
        scope: {
            position: '='
        },
        link: function (scope, ele, attrs) {
            var game = new Phaser.Game(550, 400, Phaser.AUTO, 'game-map', {
                preload: preload,
                create: create,
                update: update,
                render: render
            });

            function preload() {
                game.load.tilemap('map_'+scope.position.map, 'map/' + scope.position.map + '.json', null, Phaser.Tilemap.TILED_JSON);
                game.load.image('tiles', 'tileset/base.png');
                game.load.image('player', 'image/player.png');
            }

            var map;
            var layer;

            var cursors;
            var sprite;

            function create() {
                map = game.add.tilemap('map_'+scope.position.map);
                map.addTilesetImage('tileset', 'tiles');

                layer = map.createLayer('Ground');

                layer.resizeWorld();

                sprite = game.add.sprite(48, 48, 'player');
                sprite.anchor.setTo(0, 0);
                sprite.x = scope.position.x * 48;
                sprite.y = scope.position.y * 48;

                game.camera.follow(sprite);

                cursors = game.input.keyboard.createCursorKeys();
            }

            function update() {
            }

            function render() {
                game.debug.text('Tile X: ' + layer.getTileX(sprite.x), 32, 48, 'rgb(0,0,0)');
                game.debug.text('Tile Y: ' + layer.getTileY(sprite.y), 32, 64, 'rgb(0,0,0)');
            }

            scope.$on('$destroy', function () {
                game.destroy();
            });

            scope.$on('position', function (event, position) {
                sprite.x = position.x * 48;
                sprite.y = position.y * 48;

                //TODO: add map changing
            });
        }
    }
});