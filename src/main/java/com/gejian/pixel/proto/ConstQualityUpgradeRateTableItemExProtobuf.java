// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ConstQualityUpgradeRateTableItemEx.proto

package com.gejian.pixel.proto;

public final class ConstQualityUpgradeRateTableItemExProtobuf {
  private ConstQualityUpgradeRateTableItemExProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ConstQualityUpgradeRateTableItemExOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ConstQualityUpgradeRateTableItemEx)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>uint32 id = 1;</code>
     */
    int getId();

    /**
     * <code>uint32 quality = 2;</code>
     */
    int getQuality();

    /**
     * <code>string desc = 3;</code>
     */
    java.lang.String getDesc();
    /**
     * <code>string desc = 3;</code>
     */
    com.google.protobuf.ByteString
        getDescBytes();

    /**
     * <code>string up = 4;</code>
     */
    java.lang.String getUp();
    /**
     * <code>string up = 4;</code>
     */
    com.google.protobuf.ByteString
        getUpBytes();
  }
  /**
   * Protobuf type {@code ConstQualityUpgradeRateTableItemEx}
   */
  public  static final class ConstQualityUpgradeRateTableItemEx extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ConstQualityUpgradeRateTableItemEx)
      ConstQualityUpgradeRateTableItemExOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ConstQualityUpgradeRateTableItemEx.newBuilder() to construct.
    private ConstQualityUpgradeRateTableItemEx(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ConstQualityUpgradeRateTableItemEx() {
      id_ = 0;
      quality_ = 0;
      desc_ = "";
      up_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ConstQualityUpgradeRateTableItemEx(
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

              quality_ = input.readUInt32();
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              desc_ = s;
              break;
            }
            case 34: {
              java.lang.String s = input.readStringRequireUtf8();

              up_ = s;
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
      return com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.internal_static_ConstQualityUpgradeRateTableItemEx_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.internal_static_ConstQualityUpgradeRateTableItemEx_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx.class, com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx.Builder.class);
    }

    public static final int ID_FIELD_NUMBER = 1;
    private int id_;
    /**
     * <code>uint32 id = 1;</code>
     */
    public int getId() {
      return id_;
    }

    public static final int QUALITY_FIELD_NUMBER = 2;
    private int quality_;
    /**
     * <code>uint32 quality = 2;</code>
     */
    public int getQuality() {
      return quality_;
    }

    public static final int DESC_FIELD_NUMBER = 3;
    private volatile java.lang.Object desc_;
    /**
     * <code>string desc = 3;</code>
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
     * <code>string desc = 3;</code>
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

    public static final int UP_FIELD_NUMBER = 4;
    private volatile java.lang.Object up_;
    /**
     * <code>string up = 4;</code>
     */
    public java.lang.String getUp() {
      java.lang.Object ref = up_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        up_ = s;
        return s;
      }
    }
    /**
     * <code>string up = 4;</code>
     */
    public com.google.protobuf.ByteString
        getUpBytes() {
      java.lang.Object ref = up_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        up_ = b;
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
      if (id_ != 0) {
        output.writeUInt32(1, id_);
      }
      if (quality_ != 0) {
        output.writeUInt32(2, quality_);
      }
      if (!getDescBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, desc_);
      }
      if (!getUpBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, up_);
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
      if (quality_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, quality_);
      }
      if (!getDescBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, desc_);
      }
      if (!getUpBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, up_);
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
      if (!(obj instanceof com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx other = (com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx) obj;

      boolean result = true;
      result = result && (getId()
          == other.getId());
      result = result && (getQuality()
          == other.getQuality());
      result = result && getDesc()
          .equals(other.getDesc());
      result = result && getUp()
          .equals(other.getUp());
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
      hash = (37 * hash) + QUALITY_FIELD_NUMBER;
      hash = (53 * hash) + getQuality();
      hash = (37 * hash) + DESC_FIELD_NUMBER;
      hash = (53 * hash) + getDesc().hashCode();
      hash = (37 * hash) + UP_FIELD_NUMBER;
      hash = (53 * hash) + getUp().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx parseFrom(
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
    public static Builder newBuilder(com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx prototype) {
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
     * Protobuf type {@code ConstQualityUpgradeRateTableItemEx}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ConstQualityUpgradeRateTableItemEx)
        com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemExOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.internal_static_ConstQualityUpgradeRateTableItemEx_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.internal_static_ConstQualityUpgradeRateTableItemEx_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx.class, com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx.newBuilder()
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

        quality_ = 0;

        desc_ = "";

        up_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.internal_static_ConstQualityUpgradeRateTableItemEx_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx getDefaultInstanceForType() {
        return com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx build() {
        com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx buildPartial() {
        com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx result = new com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx(this);
        result.id_ = id_;
        result.quality_ = quality_;
        result.desc_ = desc_;
        result.up_ = up_;
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
        if (other instanceof com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx) {
          return mergeFrom((com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx other) {
        if (other == com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx.getDefaultInstance()) return this;
        if (other.getId() != 0) {
          setId(other.getId());
        }
        if (other.getQuality() != 0) {
          setQuality(other.getQuality());
        }
        if (!other.getDesc().isEmpty()) {
          desc_ = other.desc_;
          onChanged();
        }
        if (!other.getUp().isEmpty()) {
          up_ = other.up_;
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
        com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx) e.getUnfinishedMessage();
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

      private int quality_ ;
      /**
       * <code>uint32 quality = 2;</code>
       */
      public int getQuality() {
        return quality_;
      }
      /**
       * <code>uint32 quality = 2;</code>
       */
      public Builder setQuality(int value) {
        
        quality_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 quality = 2;</code>
       */
      public Builder clearQuality() {
        
        quality_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object desc_ = "";
      /**
       * <code>string desc = 3;</code>
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
       * <code>string desc = 3;</code>
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
       * <code>string desc = 3;</code>
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
       * <code>string desc = 3;</code>
       */
      public Builder clearDesc() {
        
        desc_ = getDefaultInstance().getDesc();
        onChanged();
        return this;
      }
      /**
       * <code>string desc = 3;</code>
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

      private java.lang.Object up_ = "";
      /**
       * <code>string up = 4;</code>
       */
      public java.lang.String getUp() {
        java.lang.Object ref = up_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          up_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string up = 4;</code>
       */
      public com.google.protobuf.ByteString
          getUpBytes() {
        java.lang.Object ref = up_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          up_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string up = 4;</code>
       */
      public Builder setUp(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        up_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string up = 4;</code>
       */
      public Builder clearUp() {
        
        up_ = getDefaultInstance().getUp();
        onChanged();
        return this;
      }
      /**
       * <code>string up = 4;</code>
       */
      public Builder setUpBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        up_ = value;
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


      // @@protoc_insertion_point(builder_scope:ConstQualityUpgradeRateTableItemEx)
    }

    // @@protoc_insertion_point(class_scope:ConstQualityUpgradeRateTableItemEx)
    private static final com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx();
    }

    public static com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ConstQualityUpgradeRateTableItemEx>
        PARSER = new com.google.protobuf.AbstractParser<ConstQualityUpgradeRateTableItemEx>() {
      @java.lang.Override
      public ConstQualityUpgradeRateTableItemEx parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ConstQualityUpgradeRateTableItemEx(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ConstQualityUpgradeRateTableItemEx> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ConstQualityUpgradeRateTableItemEx> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.ConstQualityUpgradeRateTableItemExProtobuf.ConstQualityUpgradeRateTableItemEx getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ConstQualityUpgradeRateTableItemEx_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ConstQualityUpgradeRateTableItemEx_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n(ConstQualityUpgradeRateTableItemEx.pro" +
      "to\"[\n\"ConstQualityUpgradeRateTableItemEx" +
      "\022\n\n\002id\030\001 \001(\r\022\017\n\007quality\030\002 \001(\r\022\014\n\004desc\030\003 " +
      "\001(\t\022\n\n\002up\030\004 \001(\tBD\n\026com.gejian.pixel.prot" +
      "oB*ConstQualityUpgradeRateTableItemExPro" +
      "tobufb\006proto3"
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
    internal_static_ConstQualityUpgradeRateTableItemEx_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ConstQualityUpgradeRateTableItemEx_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ConstQualityUpgradeRateTableItemEx_descriptor,
        new java.lang.String[] { "Id", "Quality", "Desc", "Up", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}