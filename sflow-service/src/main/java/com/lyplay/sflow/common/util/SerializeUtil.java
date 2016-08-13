package com.lyplay.sflow.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
	public static byte[] serializeObject(Object object) throws IOException {

		ByteArrayOutputStream saos = new ByteArrayOutputStream();

		ObjectOutputStream oos = new ObjectOutputStream(saos);

		oos.writeObject(object);

		oos.flush();

		return saos.toByteArray();

	}

	public static Object deserializeObject(byte[] buf) throws IOException,
			ClassNotFoundException {

		Object object = null;

		ByteArrayInputStream sais = new ByteArrayInputStream(buf);

		ObjectInputStream ois = new ObjectInputStream(sais);

		object = ois.readObject();

		return object;

	}
}
