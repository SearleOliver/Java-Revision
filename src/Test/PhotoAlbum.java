package Test;

import java.util.*;

public class PhotoAlbum implements Iterable<Photo>{
	private List<Photo> photos;
	public PhotoAlbum() {
		this.photos = new ArrayList<>();
	}
	
	public void addPhoto(Photo p) {
		photos.add(p);
	}
	
	public int size() {
		return photos.size();
	}
	
	public Photo get(int index) {
		return photos.get(index);
	}
	
	@Override
	public Iterator<Photo> iterator()
	{
		return new AlbumIterator();
	}
	
	private class AlbumIterator implements Iterator<Photo>{
		private int position = 0;
		@Override
		public boolean hasNext() {
			return position < photos.size();
		}
		@Override
		public Photo next() {
			return photos.get(position++);
		}
	}
}
	
