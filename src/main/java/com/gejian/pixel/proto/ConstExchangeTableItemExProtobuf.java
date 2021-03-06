// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ConstExchangeTableItemEx.proto

package com.gejian.pixel.proto;

public final class ConstExchangeTableItemExProtobuf {
  private ConstExchangeTableItemExProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ConstExchangeTableItemExOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ConstExchangeTableItemEx)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>uint32 type = 1;</code>
     */
    int getType();

    /**
     * <code>uint32 stone = 2;</code>
     */
    int getStone();

    /**
     * <code>uint32 amount = 3;</code>
     */
    int getAmount();

    /**
     * <code>string desc = 4;</code>
     */
    java.lang.String getDesc();
    /**
     * <code>string desc = 4;</code>
     */
    com.google.protobuf.ByteString
        getDescBytes();
  }
  /**
   * Protobuf type {@code ConstExchangeTableItemEx}
   */
  public  static final class ConstExchangeTableItemEx extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ConstExchangeTableItemEx)
      ConstExchangeTableItemExOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ConstExchangeTableItemEx.newBuilder() to construct.
    private ConstExchangeTableItemEx(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ConstExchangeTableItemEx() {
      type_ = 0;
      stone_ = 0;
      amount_ = 0;
      desc_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ConstExchangeTableItemEx(
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
            case 8: {

              type_ = input.readUInt32();
              break;
            }
            case 16: {

              stone_ = input.readUInt32();
              break;
            }
            case 24: {

              amount_ = input.readUInt32();
              break;
            }
            case 34: {
              java.lang.String s = input.readStringRequireUtf8();

              desc_ = s;
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
      return com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.internal_static_ConstExchangeTableItemEx_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.internal_static_ConstExchangeTableItemEx_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx.class, com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx.Builder.class);
    }

    public static final int TYPE_FIELD_NUMBER = 1;
    private int type_;
    /**
     * <code>uint32 type = 1;</code>
     */
    public int getType() {
      return type_;
    }

    public static final int STONE_FIELD_NUMBER = 2;
    private int stone_;
    /**
     * <code>uint32 stone = 2;</code>
     */
    public int getStone() {
      return stone_;
    }

    public static final int AMOUNT_FIELD_NUMBER = 3;
    private int amount_;
    /**
     * <code>uint32 amount = 3;</code>
     */
    public int getAmount() {
      return amount_;
    }

    public static final int DESC_FIELD_NUMBER = 4;
    private volatile java.lang.Object desc_;
    /**
     * <code>string desc = 4;</code>
     */
    public java.lang.String getDesc() {
      java.lang.Object ref = desc_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        desc_ = s;
        return s;
      }
    }
    /**
     * <code>string desc = 4;</code>
     */
    public com.google.protobuf.ByteString
        getDescBytes() {
      java.lang.Object ref = desc_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        desc_ = b;
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
        output.writeUInt32(1, type_);
      }
      if (stone_ != 0) {
        output.writeUInt32(2, stone_);
      }
      if (amount_ != 0) {
        output.writeUInt32(3, amount_);
      }
      if (!getDescBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, desc_);
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
          .computeUInt32Size(1, type_);
      }
      if (stone_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, stone_);
      }
      if (amount_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(3, amount_);
      }
      if (!getDescBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, desc_);
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
      if (!(obj instanceof com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx other = (com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx) obj;

      boolean result = true;
      result = result && (getType()
          == other.getType());
      result = result && (getStone()
          == other.getStone());
      result = result && (getAmount()
          == other.getAmount());
      result = result && getDesc()
          .equals(other.getDesc());
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
      hash = (37 * hash) + STONE_FIELD_NUMBER;
      hash = (53 * hash) + getStone();
      hash = (37 * hash) + AMOUNT_FIELD_NUMBER;
      hash = (53 * hash) + getAmount();
      hash = (37 * hash) + DESC_FIELD_NUMBER;
      hash = (53 * hash) + getDesc().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx parseFrom(
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
    public static Builder newBuilder(com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx prototype) {
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
     * Protobuf type {@code ConstExchangeTableItemEx}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ConstExchangeTableItemEx)
        com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemExOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.internal_static_ConstExchangeTableItemEx_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.internal_static_ConstExchangeTableItemEx_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx.class, com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx.newBuilder()
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

        stone_ = 0;

        amount_ = 0;

        desc_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.internal_static_ConstExchangeTableItemEx_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx getDefaultInstanceForType() {
        return com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx build() {
        com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx buildPartial() {
        com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx result = new com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx(this);
        result.type_ = type_;
        result.stone_ = stone_;
        result.amount_ = amount_;
        result.desc_ = desc_;
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
        if (other instanceof com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx) {
          return mergeFrom((com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx other) {
        if (other == com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx.getDefaultInstance()) return this;
        if (other.getType() != 0) {
          setType(other.getType());
        }
        if (other.getStone() != 0) {
          setStone(other.getStone());
        }
        if (other.getAmount() != 0) {
          setAmount(other.getAmount());
        }
        if (!other.getDesc().isEmpty()) {
          desc_ = other.desc_;
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
        com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx) e.getUnfinishedMessage();
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
       * <code>uint32 type = 1;</code>
       */
      public int getType() {
        return type_;
      }
      /**
       * <code>uint32 type = 1;</code>
       */
      public Builder setType(int value) {
        
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 type = 1;</code>
       */
      public Builder clearType() {
        
        type_ = 0;
        onChanged();
        return this;
      }

      private int stone_ ;
      /**
       * <code>uint32 stone = 2;</code>
       */
      public int getStone() {
        return stone_;
      }
      /**
       * <code>uint32 stone = 2;</code>
       */
      public Builder setStone(int value) {
        
        stone_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 stone = 2;</code>
       */
      public Builder clearStone() {
        
        stone_ = 0;
        onChanged();
        return this;
      }

      private int amount_ ;
      /**
       * <code>uint32 amount = 3;</code>
       */
      public int getAmount() {
        return amount_;
      }
      /**
       * <code>uint32 amount = 3;</code>
       */
      public Builder setAmount(int value) {
        
        amount_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 amount = 3;</code>
       */
      public Builder clearAmount() {
        
        amount_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object desc_ = "";
      /**
       * <code>string desc = 4;</code>
       */
      public java.lang.String getDesc() {
        java.lang.Object ref = desc_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          desc_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string desc = 4;</code>
       */
      public com.google.protobuf.ByteString
          getDescBytes() {
        java.lang.Object ref = desc_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          desc_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string desc = 4;</code>
       */
      public Builder setDesc(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        desc_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string desc = 4;</code>
       */
      public Builder clearDesc() {
        
        desc_ = getDefaultInstance().getDesc();
        onChanged();
        return this;
      }
      /**
       * <code>string desc = 4;</code>
       */
      public Builder setDescBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        desc_ = value;
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


      // @@protoc_insertion_point(builder_scope:ConstExchangeTableItemEx)
    }

    // @@protoc_insertion_point(class_scope:ConstExchangeTableItemEx)
    private static final com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx();
    }

    public static com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ConstExchangeTableItemEx>
        PARSER = new com.google.protobuf.AbstractParser<ConstExchangeTableItemEx>() {
      @java.lang.Override
      public ConstExchangeTableItemEx parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ConstExchangeTableItemEx(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ConstExchangeTableItemEx> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ConstExchangeTableItemEx> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.ConstExchangeTableItemExProtobuf.ConstExchangeTableItemEx getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ConstExchangeTableItemEx_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ConstExchangeTableItemEx_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036ConstExchangeTableItemEx.proto\"U\n\030Cons" +
      "tExchangeTableItemEx\022\014\n\004type\030\001 \001(\r\022\r\n\005st" +
      "one\030\002 \001(\r\022\016\n\006amount\030\003 \001(\r\022\014\n\004desc\030\004 \001(\tB" +
      ":\n\026com.gejian.pixel.protoB ConstExchange" +
      "TableItemExProtobufb\006proto3"
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
    internal_static_ConstExchangeTableItemEx_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ConstExchangeTableItemEx_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ConstExchangeTableItemEx_descriptor,
        new java.lang.String[] { "Type", "Stone", "Amount", "Desc", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
