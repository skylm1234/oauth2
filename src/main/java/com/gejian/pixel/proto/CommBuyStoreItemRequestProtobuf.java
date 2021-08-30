// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CommBuyStoreItemRequest.proto

package com.gejian.pixel.proto;

public final class CommBuyStoreItemRequestProtobuf {
  private CommBuyStoreItemRequestProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface CommBuyStoreItemRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:CommBuyStoreItemRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>uint32 type = 10;</code>
     */
    int getType();

    /**
     * <code>uint32 index = 11;</code>
     */
    int getIndex();

    /**
     * <code>uint32 number = 12;</code>
     */
    int getNumber();

    /**
     * <code>string expected = 13;</code>
     */
    java.lang.String getExpected();
    /**
     * <code>string expected = 13;</code>
     */
    com.google.protobuf.ByteString
        getExpectedBytes();
  }
  /**
   * Protobuf type {@code CommBuyStoreItemRequest}
   */
  public  static final class CommBuyStoreItemRequest extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:CommBuyStoreItemRequest)
      CommBuyStoreItemRequestOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use CommBuyStoreItemRequest.newBuilder() to construct.
    private CommBuyStoreItemRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private CommBuyStoreItemRequest() {
      type_ = 0;
      index_ = 0;
      number_ = 0;
      expected_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private CommBuyStoreItemRequest(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 80: {

              type_ = input.readUInt32();
              break;
            }
            case 88: {

              index_ = input.readUInt32();
              break;
            }
            case 96: {

              number_ = input.readUInt32();
              break;
            }
            case 106: {
              java.lang.String s = input.readStringRequireUtf8();

              expected_ = s;
              break;
            }
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.internal_static_CommBuyStoreItemRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.internal_static_CommBuyStoreItemRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest.class, com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest.Builder.class);
    }

    public static final int TYPE_FIELD_NUMBER = 10;
    private int type_;
    /**
     * <code>uint32 type = 10;</code>
     */
    public int getType() {
      return type_;
    }

    public static final int INDEX_FIELD_NUMBER = 11;
    private int index_;
    /**
     * <code>uint32 index = 11;</code>
     */
    public int getIndex() {
      return index_;
    }

    public static final int NUMBER_FIELD_NUMBER = 12;
    private int number_;
    /**
     * <code>uint32 number = 12;</code>
     */
    public int getNumber() {
      return number_;
    }

    public static final int EXPECTED_FIELD_NUMBER = 13;
    private volatile java.lang.Object expected_;
    /**
     * <code>string expected = 13;</code>
     */
    public java.lang.String getExpected() {
      java.lang.Object ref = expected_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        expected_ = s;
        return s;
      }
    }
    /**
     * <code>string expected = 13;</code>
     */
    public com.google.protobuf.ByteString
        getExpectedBytes() {
      java.lang.Object ref = expected_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        expected_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (type_ != 0) {
        output.writeUInt32(10, type_);
      }
      if (index_ != 0) {
        output.writeUInt32(11, index_);
      }
      if (number_ != 0) {
        output.writeUInt32(12, number_);
      }
      if (!getExpectedBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 13, expected_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (type_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(10, type_);
      }
      if (index_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(11, index_);
      }
      if (number_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(12, number_);
      }
      if (!getExpectedBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(13, expected_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest other = (com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest) obj;

      boolean result = true;
      result = result && (getType()
          == other.getType());
      result = result && (getIndex()
          == other.getIndex());
      result = result && (getNumber()
          == other.getNumber());
      result = result && getExpected()
          .equals(other.getExpected());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getType();
      hash = (37 * hash) + INDEX_FIELD_NUMBER;
      hash = (53 * hash) + getIndex();
      hash = (37 * hash) + NUMBER_FIELD_NUMBER;
      hash = (53 * hash) + getNumber();
      hash = (37 * hash) + EXPECTED_FIELD_NUMBER;
      hash = (53 * hash) + getExpected().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code CommBuyStoreItemRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:CommBuyStoreItemRequest)
        com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.internal_static_CommBuyStoreItemRequest_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.internal_static_CommBuyStoreItemRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest.class, com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        type_ = 0;

        index_ = 0;

        number_ = 0;

        expected_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.internal_static_CommBuyStoreItemRequest_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest getDefaultInstanceForType() {
        return com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest build() {
        com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest buildPartial() {
        com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest result = new com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest(this);
        result.type_ = type_;
        result.index_ = index_;
        result.number_ = number_;
        result.expected_ = expected_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return (Builder) super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest) {
          return mergeFrom((com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest other) {
        if (other == com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest.getDefaultInstance()) return this;
        if (other.getType() != 0) {
          setType(other.getType());
        }
        if (other.getIndex() != 0) {
          setIndex(other.getIndex());
        }
        if (other.getNumber() != 0) {
          setNumber(other.getNumber());
        }
        if (!other.getExpected().isEmpty()) {
          expected_ = other.expected_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int type_ ;
      /**
       * <code>uint32 type = 10;</code>
       */
      public int getType() {
        return type_;
      }
      /**
       * <code>uint32 type = 10;</code>
       */
      public Builder setType(int value) {
        
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 type = 10;</code>
       */
      public Builder clearType() {
        
        type_ = 0;
        onChanged();
        return this;
      }

      private int index_ ;
      /**
       * <code>uint32 index = 11;</code>
       */
      public int getIndex() {
        return index_;
      }
      /**
       * <code>uint32 index = 11;</code>
       */
      public Builder setIndex(int value) {
        
        index_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 index = 11;</code>
       */
      public Builder clearIndex() {
        
        index_ = 0;
        onChanged();
        return this;
      }

      private int number_ ;
      /**
       * <code>uint32 number = 12;</code>
       */
      public int getNumber() {
        return number_;
      }
      /**
       * <code>uint32 number = 12;</code>
       */
      public Builder setNumber(int value) {
        
        number_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 number = 12;</code>
       */
      public Builder clearNumber() {
        
        number_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object expected_ = "";
      /**
       * <code>string expected = 13;</code>
       */
      public java.lang.String getExpected() {
        java.lang.Object ref = expected_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          expected_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string expected = 13;</code>
       */
      public com.google.protobuf.ByteString
          getExpectedBytes() {
        java.lang.Object ref = expected_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          expected_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string expected = 13;</code>
       */
      public Builder setExpected(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        expected_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string expected = 13;</code>
       */
      public Builder clearExpected() {
        
        expected_ = getDefaultInstance().getExpected();
        onChanged();
        return this;
      }
      /**
       * <code>string expected = 13;</code>
       */
      public Builder setExpectedBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        expected_ = value;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:CommBuyStoreItemRequest)
    }

    // @@protoc_insertion_point(class_scope:CommBuyStoreItemRequest)
    private static final com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest();
    }

    public static com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<CommBuyStoreItemRequest>
        PARSER = new com.google.protobuf.AbstractParser<CommBuyStoreItemRequest>() {
      @java.lang.Override
      public CommBuyStoreItemRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new CommBuyStoreItemRequest(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<CommBuyStoreItemRequest> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<CommBuyStoreItemRequest> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CommBuyStoreItemRequest_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CommBuyStoreItemRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\035CommBuyStoreItemRequest.proto\"X\n\027CommB" +
      "uyStoreItemRequest\022\014\n\004type\030\n \001(\r\022\r\n\005inde" +
      "x\030\013 \001(\r\022\016\n\006number\030\014 \001(\r\022\020\n\010expected\030\r \001(" +
      "\tB9\n\026com.gejian.pixel.protoB\037CommBuyStor" +
      "eItemRequestProtobufb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_CommBuyStoreItemRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_CommBuyStoreItemRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommBuyStoreItemRequest_descriptor,
        new java.lang.String[] { "Type", "Index", "Number", "Expected", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}