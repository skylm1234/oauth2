// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Backpack.proto

package com.gejian.pixel.proto;

public final class BackpackProtobuf {
  private BackpackProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface BackpackOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Backpack)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     **等级
     * </pre>
     *
     * <code>uint32 level = 1;</code>
     */
    int getLevel();

    /**
     * <pre>
     **金币max
     * </pre>
     *
     * <code>uint32 gold_max = 2;</code>
     */
    int getGoldMax();

    /**
     * <pre>
     **经验max
     * </pre>
     *
     * <code>uint32 exp_max = 3;</code>
     */
    int getExpMax();

    /**
     * <pre>
     **物品max个数
     * </pre>
     *
     * <code>uint32 item_max = 4;</code>
     */
    int getItemMax();

    /**
     * <pre>
     **升级需求公式
     * </pre>
     *
     * <code>string prerequests = 5;</code>
     */
    java.lang.String getPrerequests();
    /**
     * <pre>
     **升级需求公式
     * </pre>
     *
     * <code>string prerequests = 5;</code>
     */
    com.google.protobuf.ByteString
        getPrerequestsBytes();
  }
  /**
   * Protobuf type {@code Backpack}
   */
  public  static final class Backpack extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:Backpack)
      BackpackOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Backpack.newBuilder() to construct.
    private Backpack(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Backpack() {
      level_ = 0;
      goldMax_ = 0;
      expMax_ = 0;
      itemMax_ = 0;
      prerequests_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Backpack(
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

              level_ = input.readUInt32();
              break;
            }
            case 16: {

              goldMax_ = input.readUInt32();
              break;
            }
            case 24: {

              expMax_ = input.readUInt32();
              break;
            }
            case 32: {

              itemMax_ = input.readUInt32();
              break;
            }
            case 42: {
              java.lang.String s = input.readStringRequireUtf8();

              prerequests_ = s;
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
      return com.gejian.pixel.proto.BackpackProtobuf.internal_static_Backpack_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.BackpackProtobuf.internal_static_Backpack_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.BackpackProtobuf.Backpack.class, com.gejian.pixel.proto.BackpackProtobuf.Backpack.Builder.class);
    }

    public static final int LEVEL_FIELD_NUMBER = 1;
    private int level_;
    /**
     * <pre>
     **等级
     * </pre>
     *
     * <code>uint32 level = 1;</code>
     */
    public int getLevel() {
      return level_;
    }

    public static final int GOLD_MAX_FIELD_NUMBER = 2;
    private int goldMax_;
    /**
     * <pre>
     **金币max
     * </pre>
     *
     * <code>uint32 gold_max = 2;</code>
     */
    public int getGoldMax() {
      return goldMax_;
    }

    public static final int EXP_MAX_FIELD_NUMBER = 3;
    private int expMax_;
    /**
     * <pre>
     **经验max
     * </pre>
     *
     * <code>uint32 exp_max = 3;</code>
     */
    public int getExpMax() {
      return expMax_;
    }

    public static final int ITEM_MAX_FIELD_NUMBER = 4;
    private int itemMax_;
    /**
     * <pre>
     **物品max个数
     * </pre>
     *
     * <code>uint32 item_max = 4;</code>
     */
    public int getItemMax() {
      return itemMax_;
    }

    public static final int PREREQUESTS_FIELD_NUMBER = 5;
    private volatile java.lang.Object prerequests_;
    /**
     * <pre>
     **升级需求公式
     * </pre>
     *
     * <code>string prerequests = 5;</code>
     */
    public java.lang.String getPrerequests() {
      java.lang.Object ref = prerequests_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        prerequests_ = s;
        return s;
      }
    }
    /**
     * <pre>
     **升级需求公式
     * </pre>
     *
     * <code>string prerequests = 5;</code>
     */
    public com.google.protobuf.ByteString
        getPrerequestsBytes() {
      java.lang.Object ref = prerequests_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        prerequests_ = b;
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
      if (level_ != 0) {
        output.writeUInt32(1, level_);
      }
      if (goldMax_ != 0) {
        output.writeUInt32(2, goldMax_);
      }
      if (expMax_ != 0) {
        output.writeUInt32(3, expMax_);
      }
      if (itemMax_ != 0) {
        output.writeUInt32(4, itemMax_);
      }
      if (!getPrerequestsBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 5, prerequests_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (level_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(1, level_);
      }
      if (goldMax_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, goldMax_);
      }
      if (expMax_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(3, expMax_);
      }
      if (itemMax_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(4, itemMax_);
      }
      if (!getPrerequestsBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, prerequests_);
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
      if (!(obj instanceof com.gejian.pixel.proto.BackpackProtobuf.Backpack)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.BackpackProtobuf.Backpack other = (com.gejian.pixel.proto.BackpackProtobuf.Backpack) obj;

      boolean result = true;
      result = result && (getLevel()
          == other.getLevel());
      result = result && (getGoldMax()
          == other.getGoldMax());
      result = result && (getExpMax()
          == other.getExpMax());
      result = result && (getItemMax()
          == other.getItemMax());
      result = result && getPrerequests()
          .equals(other.getPrerequests());
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
      hash = (37 * hash) + LEVEL_FIELD_NUMBER;
      hash = (53 * hash) + getLevel();
      hash = (37 * hash) + GOLD_MAX_FIELD_NUMBER;
      hash = (53 * hash) + getGoldMax();
      hash = (37 * hash) + EXP_MAX_FIELD_NUMBER;
      hash = (53 * hash) + getExpMax();
      hash = (37 * hash) + ITEM_MAX_FIELD_NUMBER;
      hash = (53 * hash) + getItemMax();
      hash = (37 * hash) + PREREQUESTS_FIELD_NUMBER;
      hash = (53 * hash) + getPrerequests().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.gejian.pixel.proto.BackpackProtobuf.Backpack parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.BackpackProtobuf.Backpack parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.BackpackProtobuf.Backpack parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.BackpackProtobuf.Backpack parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.BackpackProtobuf.Backpack parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.BackpackProtobuf.Backpack parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.BackpackProtobuf.Backpack parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.BackpackProtobuf.Backpack parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.BackpackProtobuf.Backpack parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.BackpackProtobuf.Backpack parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.BackpackProtobuf.Backpack parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.BackpackProtobuf.Backpack parseFrom(
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
    public static Builder newBuilder(com.gejian.pixel.proto.BackpackProtobuf.Backpack prototype) {
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
     * Protobuf type {@code Backpack}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Backpack)
        com.gejian.pixel.proto.BackpackProtobuf.BackpackOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.BackpackProtobuf.internal_static_Backpack_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.BackpackProtobuf.internal_static_Backpack_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.BackpackProtobuf.Backpack.class, com.gejian.pixel.proto.BackpackProtobuf.Backpack.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.BackpackProtobuf.Backpack.newBuilder()
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
        level_ = 0;

        goldMax_ = 0;

        expMax_ = 0;

        itemMax_ = 0;

        prerequests_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gejian.pixel.proto.BackpackProtobuf.internal_static_Backpack_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.BackpackProtobuf.Backpack getDefaultInstanceForType() {
        return com.gejian.pixel.proto.BackpackProtobuf.Backpack.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.BackpackProtobuf.Backpack build() {
        com.gejian.pixel.proto.BackpackProtobuf.Backpack result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.BackpackProtobuf.Backpack buildPartial() {
        com.gejian.pixel.proto.BackpackProtobuf.Backpack result = new com.gejian.pixel.proto.BackpackProtobuf.Backpack(this);
        result.level_ = level_;
        result.goldMax_ = goldMax_;
        result.expMax_ = expMax_;
        result.itemMax_ = itemMax_;
        result.prerequests_ = prerequests_;
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
        if (other instanceof com.gejian.pixel.proto.BackpackProtobuf.Backpack) {
          return mergeFrom((com.gejian.pixel.proto.BackpackProtobuf.Backpack)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.BackpackProtobuf.Backpack other) {
        if (other == com.gejian.pixel.proto.BackpackProtobuf.Backpack.getDefaultInstance()) return this;
        if (other.getLevel() != 0) {
          setLevel(other.getLevel());
        }
        if (other.getGoldMax() != 0) {
          setGoldMax(other.getGoldMax());
        }
        if (other.getExpMax() != 0) {
          setExpMax(other.getExpMax());
        }
        if (other.getItemMax() != 0) {
          setItemMax(other.getItemMax());
        }
        if (!other.getPrerequests().isEmpty()) {
          prerequests_ = other.prerequests_;
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
        com.gejian.pixel.proto.BackpackProtobuf.Backpack parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.BackpackProtobuf.Backpack) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int level_ ;
      /**
       * <pre>
       **等级
       * </pre>
       *
       * <code>uint32 level = 1;</code>
       */
      public int getLevel() {
        return level_;
      }
      /**
       * <pre>
       **等级
       * </pre>
       *
       * <code>uint32 level = 1;</code>
       */
      public Builder setLevel(int value) {
        
        level_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       **等级
       * </pre>
       *
       * <code>uint32 level = 1;</code>
       */
      public Builder clearLevel() {
        
        level_ = 0;
        onChanged();
        return this;
      }

      private int goldMax_ ;
      /**
       * <pre>
       **金币max
       * </pre>
       *
       * <code>uint32 gold_max = 2;</code>
       */
      public int getGoldMax() {
        return goldMax_;
      }
      /**
       * <pre>
       **金币max
       * </pre>
       *
       * <code>uint32 gold_max = 2;</code>
       */
      public Builder setGoldMax(int value) {
        
        goldMax_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       **金币max
       * </pre>
       *
       * <code>uint32 gold_max = 2;</code>
       */
      public Builder clearGoldMax() {
        
        goldMax_ = 0;
        onChanged();
        return this;
      }

      private int expMax_ ;
      /**
       * <pre>
       **经验max
       * </pre>
       *
       * <code>uint32 exp_max = 3;</code>
       */
      public int getExpMax() {
        return expMax_;
      }
      /**
       * <pre>
       **经验max
       * </pre>
       *
       * <code>uint32 exp_max = 3;</code>
       */
      public Builder setExpMax(int value) {
        
        expMax_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       **经验max
       * </pre>
       *
       * <code>uint32 exp_max = 3;</code>
       */
      public Builder clearExpMax() {
        
        expMax_ = 0;
        onChanged();
        return this;
      }

      private int itemMax_ ;
      /**
       * <pre>
       **物品max个数
       * </pre>
       *
       * <code>uint32 item_max = 4;</code>
       */
      public int getItemMax() {
        return itemMax_;
      }
      /**
       * <pre>
       **物品max个数
       * </pre>
       *
       * <code>uint32 item_max = 4;</code>
       */
      public Builder setItemMax(int value) {
        
        itemMax_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       **物品max个数
       * </pre>
       *
       * <code>uint32 item_max = 4;</code>
       */
      public Builder clearItemMax() {
        
        itemMax_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object prerequests_ = "";
      /**
       * <pre>
       **升级需求公式
       * </pre>
       *
       * <code>string prerequests = 5;</code>
       */
      public java.lang.String getPrerequests() {
        java.lang.Object ref = prerequests_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          prerequests_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       **升级需求公式
       * </pre>
       *
       * <code>string prerequests = 5;</code>
       */
      public com.google.protobuf.ByteString
          getPrerequestsBytes() {
        java.lang.Object ref = prerequests_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          prerequests_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       **升级需求公式
       * </pre>
       *
       * <code>string prerequests = 5;</code>
       */
      public Builder setPrerequests(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        prerequests_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       **升级需求公式
       * </pre>
       *
       * <code>string prerequests = 5;</code>
       */
      public Builder clearPrerequests() {
        
        prerequests_ = getDefaultInstance().getPrerequests();
        onChanged();
        return this;
      }
      /**
       * <pre>
       **升级需求公式
       * </pre>
       *
       * <code>string prerequests = 5;</code>
       */
      public Builder setPrerequestsBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        prerequests_ = value;
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


      // @@protoc_insertion_point(builder_scope:Backpack)
    }

    // @@protoc_insertion_point(class_scope:Backpack)
    private static final com.gejian.pixel.proto.BackpackProtobuf.Backpack DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.BackpackProtobuf.Backpack();
    }

    public static com.gejian.pixel.proto.BackpackProtobuf.Backpack getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Backpack>
        PARSER = new com.google.protobuf.AbstractParser<Backpack>() {
      @java.lang.Override
      public Backpack parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Backpack(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Backpack> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Backpack> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.BackpackProtobuf.Backpack getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Backpack_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Backpack_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016Backpack.proto\"c\n\010Backpack\022\r\n\005level\030\001 " +
      "\001(\r\022\020\n\010gold_max\030\002 \001(\r\022\017\n\007exp_max\030\003 \001(\r\022\020" +
      "\n\010item_max\030\004 \001(\r\022\023\n\013prerequests\030\005 \001(\tB*\n" +
      "\026com.gejian.pixel.protoB\020BackpackProtobu" +
      "fb\006proto3"
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
    internal_static_Backpack_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Backpack_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Backpack_descriptor,
        new java.lang.String[] { "Level", "GoldMax", "ExpMax", "ItemMax", "Prerequests", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}