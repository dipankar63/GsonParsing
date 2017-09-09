package com.example.springlearning;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class StringTypeAdapter {
	/*
	 * TypeAdapterEnum - Enum type to hold all types of Adapter /
	 */
	
	public enum TypeAdapterEnum{
		STRING_TO_INT_PRIM(new StringToIntegerAdapter(true)) , 
		STRING_TO_INT(new StringToIntegerAdapter(false)), 
		STRING_TO_FLOAT_PRIM(new StringToFloatAdapter(true)) , 
		STRING_TO_FLOAT(new StringToIntegerAdapter(false)) , 
		STRING_TO_DOUBLE_PRIM(new StringToIntegerAdapter(true)), 
		STRING_TO_DOUBLE(new StringToIntegerAdapter(false));
		
		private TypeAdapter<Number> typeAdapter;
		
	    TypeAdapterEnum(TypeAdapter<Number> typeAdapter) {
			this.typeAdapter = typeAdapter;
		}
	    
	    public TypeAdapter<Number>  getType(){
	    	return typeAdapter;
	    }
		
	}
	
	/*
	 * StringToIntegerAdapter - To handle Runtime Exception by returning 0 /
	 * null
	 */
	
	public static class StringToIntegerAdapter extends TypeAdapter<Number> {
		private boolean mIsPrimitive;

		public StringToIntegerAdapter(boolean isPrimitive) {
			mIsPrimitive = isPrimitive;
		}

		@Override
		public void write(JsonWriter out, Number value) throws IOException {
			if (value == null) {
				out.nullValue();
				return;
			}
			out.value(value);
		}

		@Override
		public Number read(JsonReader in) throws IOException {
			String value = in.nextString();
			Integer result = null;
			try {
				result = Integer.parseInt(value);
			} catch (NumberFormatException e) {
				if (mIsPrimitive) {
					return 0;
				}
				return result;
			}
			return result;
		}
	}

	/*
	 * StringToIntegerAdapter - To handle Runtime Exception by returning 0 /
	 * null
	 */
	public static class StringToDouble extends TypeAdapter<Number> {
		private boolean mIsPrimitive;

		public StringToDouble(boolean isPrimitive) {
			mIsPrimitive = isPrimitive;
		}

		@Override
		public void write(JsonWriter out, Number value) throws IOException {
			if (value == null) {
				out.nullValue();
				return;
			}
			out.value(value);
		}

		@Override
		public Number read(JsonReader in) throws IOException {
			String value = in.nextString();
			Double result = null;
			try {
				result = Double.valueOf(value);
			} catch (NumberFormatException e) {
				if (mIsPrimitive) {
					return (double) 0;
				}
				return result;
			}
			return result;
		}
	}

	/*
	 * To handle Runtime Exception by returning 0 / 1 null
	 */
	public static class StringToFloatAdapter extends TypeAdapter<Number> {
		private boolean mIsPrimitive;

		public StringToFloatAdapter(boolean isPrimitive) {
			mIsPrimitive = isPrimitive;
		}

		@Override
		public void write(JsonWriter out, Number value) throws IOException {
			if (value == null) {
				out.nullValue();
				return;
			}
			out.value(value);
		}

		@Override
		public Number read(JsonReader in) throws IOException {
			String value = in.nextString();
			Float result = null;
			try {
				result = Float.valueOf(value);
			} catch (NumberFormatException e) {
				if (mIsPrimitive) {
					return 0f;
				}
				return result;
			}
			return result;
		}
	}

}
