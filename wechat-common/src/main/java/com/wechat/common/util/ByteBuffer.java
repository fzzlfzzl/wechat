package com.wechat.common.util;

public class ByteBuffer {

	private int size = 4096;
	private int curSize = 0;
	private byte[] buffer = new byte[size];

	public void push(byte[] data) {
		push(data, 0, data.length);
	}

	public void push(byte[] data, int start, int length) {
		if (!isEnough(length)) {
			resize(length);
		}
		System.arraycopy(data, start, buffer, curSize, length);
		curSize += length;
	}

	private void resize(int length) {
		int need = curSize + length;
		while (true) {
			size *= 2;
			if (size > need) {
				break;
			}
		}
		byte[] origBuffer = buffer;
		buffer = new byte[size];
		System.arraycopy(origBuffer, 0, buffer, 0, curSize);
	}

	private boolean isEnough(int length) {
		int rest = size - curSize;
		return rest >= length;
	}

	public byte[] get() {
		return buffer;
	}

	public byte get(int idx) {
		return buffer[idx];
	}

	public int pop(byte[] data) {
		throw new RuntimeException("Not Implemented");
	}

	public int getCurSize() {
		return curSize;
	}

}
