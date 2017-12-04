package org.seckill.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializaUtils {
	public static byte[] serialize(Object object) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		byte[] bytes = null;
		try {
			ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
			objectStream.writeObject(object);
			bytes = byteStream.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}
	
	public static Object deserialize(byte[] bytes) {
		ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
		Object object = null;
		try {
			ObjectInputStream objectStream = new ObjectInputStream(byteStream);
			try {
				object = objectStream.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
}
