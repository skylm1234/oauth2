// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ConstNewStoreHotTableItemEx.proto

package com.gejian.pixel.proto;

public final class ConstNewStoreHotTableItemExProtobuf {
  private ConstNewStoreHotTableItemExProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ConstNewStoreHotTableItemExOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ConstNewStoreHotTableItemEx)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>uint32 id = 1;</code>
     */
    int getId();

    /**
     * <code>uint32 places = 2;</code>
     */
    int getPlaces();

    /**
     * <code>.ConstNewStoreHotTableItemExGoodFomula good_fomula = 3;</code>
     */
    boolean hasGoodFomula();
    /**
     * <code>.ConstNewStoreHotTableItemExGoodFomula good_fomula = 3;</code>
     */
    com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula getGoodFomula();
    /**
     * <code>.ConstNewStoreHotTableItemExGoodFomula good_fomula = 3;</code>
     */
    com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomulaOrBuilder getGoodFomulaOrBuilder();

    /**
     * <code>string items = 4;</code>
     */
    java.lang.String getItems();
    /**
     * <code>string items = 4;</code>
     */
    com.google.protobuf.ByteString
        getItemsBytes();

    /**
     * <code>uint32 type = 5;</code>
     */
    int getType();
  }
  /**
   * Protobuf type {@code ConstNewStoreHotTableItemEx}
   */
  public  static final class ConstNewStoreHotTableItemEx extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ConstNewStoreHotTableItemEx)
      ConstNewStoreHotTableItemExOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ConstNewStoreHotTableItemEx.newBuilder() to construct.
    private ConstNewStoreHotTableItemEx(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ConstNewStoreHotTableItemEx() {
      id_ = 0;
      places_ = 0;
      items_ = "";
      type_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ConstNewStoreHotTableItemEx(
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

              id_ = input.readUInt32();
              break;
            }
            case 16: {

              places_ = input.readUInt32();
              break;
            }
            case 26: {
              com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula.Builder subBuilder = null;
              if (goodFomula_ != null) {
                subBuilder = goodFomula_.toBuilder();
              }
              goodFomula_ = input.readMessage(com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula.parser(), extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(goodFomula_);
                goodFomula_ = subBuilder.buildPartial();
              }

              break;
            }
            case 34: {
              java.lang.String s = input.readStringRequireUtf8();

              items_ = s;
              break;
            }
            case 40: {

              type_ = input.readUInt32();
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
      return com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.internal_static_ConstNewStoreHotTableItemEx_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.internal_static_ConstNewStoreHotTableItemEx_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx.class, com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx.Builder.class);
    }

    public static final int ID_FIELD_NUMBER = 1;
    private int id_;
    /**
     * <code>uint32 id = 1;</code>
     */
    public int getId() {
      return id_;
    }

    public static final int PLACES_FIELD_NUMBER = 2;
    private int places_;
    /**
     * <code>uint32 places = 2;</code>
     */
    public int getPlaces() {
      return places_;
    }

    public static final int GOOD_FOMULA_FIELD_NUMBER = 3;
    private com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula goodFomula_;
    /**
     * <code>.ConstNewStoreHotTableItemExGoodFomula good_fomula = 3;</code>
     */
    public boolean hasGoodFomula() {
      return goodFomula_ != null;
    }
    /**
     * <code>.ConstNewStoreHotTableItemExGoodFomula good_fomula = 3;</code>
     */
    public com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula getGoodFomula() {
      return goodFomula_ == null ? com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula.getDefaultInstance() : goodFomula_;
    }
    /**
     * <code>.ConstNewStoreHotTableItemExGoodFomula good_fomula = 3;</code>
     */
    public com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomulaOrBuilder getGoodFomulaOrBuilder() {
      return getGoodFomula();
    }

    public static final int ITEMS_FIELD_NUMBER = 4;
    private volatile java.lang.Object items_;
    /**
     * <code>string items = 4;</code>
     */
    public java.lang.String getItems() {
      java.lang.Object ref = items_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        items_ = s;
        return s;
      }
    }
    /**
     * <code>string items = 4;</code>
     */
    public com.google.protobuf.ByteString
        getItemsBytes() {
      java.lang.Object ref = items_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        items_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TYPE_FIELD_NUMBER = 5;
    private int type_;
    /**
     * <code>uint32 type = 5;</code>
     */
    public int getType() {
      return type_;
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
      if (id_ != 0) {
        output.writeUInt32(1, id_);
      }
      if (places_ != 0) {
        output.writeUInt32(2, places_);
      }
      if (goodFomula_ != null) {
        output.writeMessage(3, getGoodFomula());
      }
      if (!getItemsBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, items_);
      }
      if (type_ != 0) {
        output.writeUInt32(5, type_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (id_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(1, id_);
      }
      if (places_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, places_);
      }
      if (goodFomula_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(3, getGoodFomula());
      }
      if (!getItemsBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, items_);
      }
      if (type_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(5, type_);
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
      if (!(obj instanceof com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx other = (com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx) obj;

      boolean result = true;
      result = result && (getId()
          == other.getId());
      result = result && (getPlaces()
          == other.getPlaces());
      result = result && (hasGoodFomula() == other.hasGoodFomula());
      if (hasGoodFomula()) {
        result = result && getGoodFomula()
            .equals(other.getGoodFomula());
      }
      result = result && getItems()
          .equals(other.getItems());
      result = result && (getType()
          == other.getType());
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
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getId();
      hash = (37 * hash) + PLACES_FIELD_NUMBER;
      hash = (53 * hash) + getPlaces();
      if (hasGoodFomula()) {
        hash = (37 * hash) + GOOD_FOMULA_FIELD_NUMBER;
        hash = (53 * hash) + getGoodFomula().hashCode();
      }
      hash = (37 * hash) + ITEMS_FIELD_NUMBER;
      hash = (53 * hash) + getItems().hashCode();
      hash = (37 * hash) + TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getType();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx parseFrom(
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
    public static Builder newBuilder(com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx prototype) {
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
     * Protobuf type {@code ConstNewStoreHotTableItemEx}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ConstNewStoreHotTableItemEx)
        com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemExOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.internal_static_ConstNewStoreHotTableItemEx_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.internal_static_ConstNewStoreHotTableItemEx_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx.class, com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx.newBuilder()
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
        id_ = 0;

        places_ = 0;

        if (goodFomulaBuilder_ == null) {
          goodFomula_ = null;
        } else {
          goodFomula_ = null;
          goodFomulaBuilder_ = null;
        }
        items_ = "";

        type_ = 0;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.internal_static_ConstNewStoreHotTableItemEx_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx getDefaultInstanceForType() {
        return com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx build() {
        com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx buildPartial() {
        com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx result = new com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx(this);
        result.id_ = id_;
        result.places_ = places_;
        if (goodFomulaBuilder_ == null) {
          result.goodFomula_ = goodFomula_;
        } else {
          result.goodFomula_ = goodFomulaBuilder_.build();
        }
        result.items_ = items_;
        result.type_ = type_;
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
        if (other instanceof com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx) {
          return mergeFrom((com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx other) {
        if (other == com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx.getDefaultInstance()) return this;
        if (other.getId() != 0) {
          setId(other.getId());
        }
        if (other.getPlaces() != 0) {
          setPlaces(other.getPlaces());
        }
        if (other.hasGoodFomula()) {
          mergeGoodFomula(other.getGoodFomula());
        }
        if (!other.getItems().isEmpty()) {
          items_ = other.items_;
          onChanged();
        }
        if (other.getType() != 0) {
          setType(other.getType());
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
        com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int id_ ;
      /**
       * <code>uint32 id = 1;</code>
       */
      public int getId() {
        return id_;
      }
      /**
       * <code>uint32 id = 1;</code>
       */
      public Builder setId(int value) {
        
        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 id = 1;</code>
       */
      public Builder clearId() {
        
        id_ = 0;
        onChanged();
        return this;
      }

      private int places_ ;
      /**
       * <code>uint32 places = 2;</code>
       */
      public int getPlaces() {
        return places_;
      }
      /**
       * <code>uint32 places = 2;</code>
       */
      public Builder setPlaces(int value) {
        
        places_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 places = 2;</code>
       */
      public Builder clearPlaces() {
        
        places_ = 0;
        onChanged();
        return this;
      }

      private com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula goodFomula_ = null;
      private com.google.protobuf.SingleFieldBuilderV3<
          com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula, com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula.Builder, com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomulaOrBuilder> goodFomulaBuilder_;
      /**
       * <code>.ConstNewStoreHotTableItemExGoodFomula good_fomula = 3;</code>
       */
      public boolean hasGoodFomula() {
        return goodFomulaBuilder_ != null || goodFomula_ != null;
      }
      /**
       * <code>.ConstNewStoreHotTableItemExGoodFomula good_fomula = 3;</code>
       */
      public com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula getGoodFomula() {
        if (goodFomulaBuilder_ == null) {
          return goodFomula_ == null ? com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula.getDefaultInstance() : goodFomula_;
        } else {
          return goodFomulaBuilder_.getMessage();
        }
      }
      /**
       * <code>.ConstNewStoreHotTableItemExGoodFomula good_fomula = 3;</code>
       */
      public Builder setGoodFomula(com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula value) {
        if (goodFomulaBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          goodFomula_ = value;
          onChanged();
        } else {
          goodFomulaBuilder_.setMessage(value);
        }

        return this;
      }
      /**
       * <code>.ConstNewStoreHotTableItemExGoodFomula good_fomula = 3;</code>
       */
      public Builder setGoodFomula(
          com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula.Builder builderForValue) {
        if (goodFomulaBuilder_ == null) {
          goodFomula_ = builderForValue.build();
          onChanged();
        } else {
          goodFomulaBuilder_.setMessage(builderForValue.build());
        }

        return this;
      }
      /**
       * <code>.ConstNewStoreHotTableItemExGoodFomula good_fomula = 3;</code>
       */
      public Builder mergeGoodFomula(com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula value) {
        if (goodFomulaBuilder_ == null) {
          if (goodFomula_ != null) {
            goodFomula_ =
              com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula.newBuilder(goodFomula_).mergeFrom(value).buildPartial();
          } else {
            goodFomula_ = value;
          }
          onChanged();
        } else {
          goodFomulaBuilder_.mergeFrom(value);
        }

        return this;
      }
      /**
       * <code>.ConstNewStoreHotTableItemExGoodFomula good_fomula = 3;</code>
       */
      public Builder clearGoodFomula() {
        if (goodFomulaBuilder_ == null) {
          goodFomula_ = null;
          onChanged();
        } else {
          goodFomula_ = null;
          goodFomulaBuilder_ = null;
        }

        return this;
      }
      /**
       * <code>.ConstNewStoreHotTableItemExGoodFomula good_fomula = 3;</code>
       */
      public com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula.Builder getGoodFomulaBuilder() {
        
        onChanged();
        return getGoodFomulaFieldBuilder().getBuilder();
      }
      /**
       * <code>.ConstNewStoreHotTableItemExGoodFomula good_fomula = 3;</code>
       */
      public com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomulaOrBuilder getGoodFomulaOrBuilder() {
        if (goodFomulaBuilder_ != null) {
          return goodFomulaBuilder_.getMessageOrBuilder();
        } else {
          return goodFomula_ == null ?
              com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula.getDefaultInstance() : goodFomula_;
        }
      }
      /**
       * <code>.ConstNewStoreHotTableItemExGoodFomula good_fomula = 3;</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula, com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula.Builder, com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomulaOrBuilder> 
          getGoodFomulaFieldBuilder() {
        if (goodFomulaBuilder_ == null) {
          goodFomulaBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula, com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomula.Builder, com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.ConstNewStoreHotTableItemExGoodFomulaOrBuilder>(
                  getGoodFomula(),
                  getParentForChildren(),
                  isClean());
          goodFomula_ = null;
        }
        return goodFomulaBuilder_;
      }

      private java.lang.Object items_ = "";
      /**
       * <code>string items = 4;</code>
       */
      public java.lang.String getItems() {
        java.lang.Object ref = items_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          items_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string items = 4;</code>
       */
      public com.google.protobuf.ByteString
          getItemsBytes() {
        java.lang.Object ref = items_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          items_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string items = 4;</code>
       */
      public Builder setItems(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        items_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string items = 4;</code>
       */
      public Builder clearItems() {
        
        items_ = getDefaultInstance().getItems();
        onChanged();
        return this;
      }
      /**
       * <code>string items = 4;</code>
       */
      public Builder setItemsBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        items_ = value;
        onChanged();
        return this;
      }

      private int type_ ;
      /**
       * <code>uint32 type = 5;</code>
       */
      public int getType() {
        return type_;
      }
      /**
       * <code>uint32 type = 5;</code>
       */
      public Builder setType(int value) {
        
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 type = 5;</code>
       */
      public Builder clearType() {
        
        type_ = 0;
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


      // @@protoc_insertion_point(builder_scope:ConstNewStoreHotTableItemEx)
    }

    // @@protoc_insertion_point(class_scope:ConstNewStoreHotTableItemEx)
    private static final com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx();
    }

    public static com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ConstNewStoreHotTableItemEx>
        PARSER = new com.google.protobuf.AbstractParser<ConstNewStoreHotTableItemEx>() {
      @java.lang.Override
      public ConstNewStoreHotTableItemEx parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ConstNewStoreHotTableItemEx(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ConstNewStoreHotTableItemEx> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ConstNewStoreHotTableItemEx> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.ConstNewStoreHotTableItemExProtobuf.ConstNewStoreHotTableItemEx getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ConstNewStoreHotTableItemEx_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ConstNewStoreHotTableItemEx_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n!ConstNewStoreHotTableItemEx.proto\032+Con" +
      "stNewStoreHotTableItemExGoodFomula.proto" +
      "\"\223\001\n\033ConstNewStoreHotTableItemEx\022\n\n\002id\030\001" +
      " \001(\r\022\016\n\006places\030\002 \001(\r\022;\n\013good_fomula\030\003 \001(" +
      "\0132&.ConstNewStoreHotTableItemExGoodFomul" +
      "a\022\r\n\005items\030\004 \001(\t\022\014\n\004type\030\005 \001(\rB=\n\026com.ge" +
      "jian.pixel.protoB#ConstNewStoreHotTableI" +
      "temExProtobufb\006proto3"
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
          com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.getDescriptor(),
        }, assigner);
    internal_static_ConstNewStoreHotTableItemEx_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ConstNewStoreHotTableItemEx_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ConstNewStoreHotTableItemEx_descriptor,
        new java.lang.String[] { "Id", "Places", "GoodFomula", "Items", "Type", });
    com.gejian.pixel.proto.ConstNewStoreHotTableItemExGoodFomulaProtobuf.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}