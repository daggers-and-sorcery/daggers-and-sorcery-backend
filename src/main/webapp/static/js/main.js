window.createGame = function(scope, injector) {

                var game = new Phaser.Game(550, 400, Phaser.AUTO, 'game-map', { preload: preload, create: create, update: update, render: render });

                function preload() {
                    game.load.tilemap('map_1', 'map/1.json', null, Phaser.Tilemap.TILED_JSON);
                    game.load.image('tiles', 'tileset/base.png');
                    game.load.image('player', 'image/player.png');
                }

                var map;
                var layer;

                var cursors;
                var sprite;

                function create() {
                    map = game.add.tilemap('map_1');
                    map.addTilesetImage('tileset', 'tiles');

                    layer = map.createLayer('Tile Layer 1');

                    layer.resizeWorld();

                    sprite = game.add.sprite(48, 48, 'player');
                    sprite.anchor.setTo(0.5, 0.5);

                    game.camera.follow(sprite);

                    cursors = game.input.keyboard.createCursorKeys();
                }

                function update() {
                }

                function render() {
                    game.debug.text('Tile X: ' + layer.getTileX(sprite.x), 32, 48, 'rgb(0,0,0)');
                    game.debug.text('Tile Y: ' + layer.getTileY(sprite.y), 32, 64, 'rgb(0,0,0)');
                }

                 scope.$on('$destroy', function() {
                   game.destroy();
                 });
            }

require('router/router.js');

// Controllers
require('controller/main-controller.js');

// Directives
require('directive/equals-directive.js');
require('directive/game-map-directive.js');
require('directive/attribute-list-column-directive.js');
require('directive/inventory-item-column-directive.js');

// Filters
require('filter/replace-filter.js');
require('filter/capitalize-filter.js');

// Constants
require('constant/attribute-bonus-map-constant.js');