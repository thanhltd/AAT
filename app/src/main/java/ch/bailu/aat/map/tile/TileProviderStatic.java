package ch.bailu.aat.map.tile;

/*
public class TileProviderStatic implements TileProviderInterface, Closeable {

    private final ArrayList<TileObject> tiles = new ArrayList<>(10);

    private final Source source;
    private final ServiceContext scontext;

    public TileProviderStatic(ServiceContext sc, Source s) {
        scontext = sc;
        source = s;

    }

    @Override
    public void preload(List<TilePosition> tilePositions) {
        for (TilePosition p : tilePositions) {
            get(p.tile);
        }
    }

    @Override
    public int getMaximumZoomLevel() {
        return source.getMaximumZoomLevel();
    }

    @Override
    public int getMinimumZoomLevel() {
        return source.getMinimumZoomLevel();
    }



    @Override
    public synchronized TileBitmap get(Tile tile) {
        final TileObject handle = getTileHandle(tile);

        if (handle != null) {
            return handle.getTileBitmap();
        }

        return null;
    }


    private TileObject getTileHandle(Tile tile) {
        TileObject handle = getHandleFromList(tile);

        if (handle == null) {
            handle = loadHandle(tile);

            if (handle != null)
                tiles.add(handle);
        }

        return handle;
    }


    private TileObject getHandleFromList(Tile tile) {
        for (TileObject handle: tiles) {
            if (TileObjectCache.compare(handle.getTileBitmap(), tile)) {
                return handle;
            }
        }
        return null;
    }


    private TileObject loadHandle(Tile mapTile) {
        TileObject r = null;

        if (scontext.lock()) {
            String id = source.getID(mapTile, scontext.getContext());


            ObjectHandle handle = scontext.getCacheService().getObject(
                    id,
                    source.getFactory(mapTile)
            );


            if (handle instanceof TileObject) {
                r = (TileObject) handle;
            }
            scontext.free();
        }
        return r;
    }


    @Override
    public boolean contains(Tile tile) {
        return getHandleFromList(tile) != null;
    }

    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }


    @Override
    public void setCapacity(int numNeeded) {}

    @Override
    public void reDownloadTiles() {}

    @Override
    public Source getSource() {
        return source;
    }


    public synchronized boolean isReady() {
        for (TileObject tile: tiles) {
            if (tile.isReadyAndLoaded()==false) return false;
        }
        return true;
    }


    @Override
    public synchronized void close() {
        for (TileObject tile: tiles) {
            tile.free();
        }
        tiles.clear();
    }

    @Override
    public void attach() {

    }

    @Override
    public void detach() {
        close();
    }
}
*/