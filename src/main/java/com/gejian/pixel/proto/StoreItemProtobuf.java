// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: StoreItem.proto

package com.gejian.pixel.proto;

public final class StoreItemProtobuf {
  private StoreItemProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface StoreItemOrBuilder extends
      // @@protoc_insertion_point(interface_extends:StoreItem)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string name = 10;</code>
     */
    java.lang.String getName();
    /**
     * <code>string name = 10;</code>
     */
    com.google.protobuf.ByteString
        getNameBytes();

    /**
     * <code>uint32 number = 11;</code>
     */
    int getNumber();

    /**
     * <code>string cost_type = 12;</code>
     */
    java.lang.String getCostType();
    /**
     * <code>string cost_type = 12;</code>
     */
    com.google.protobuf.ByteString
        getCostTypeBytes();

    /**
     * <code>uint32 cost_number = 13;</code>
     */
    int getCostNumber();
  }
  /**
   * Protobuf type {@code StoreItem}
   */
  public  static final class StoreItem extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:StoreItem)
      StoreItemOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use StoreItem.newBuilder() to construct.
    private StoreItem(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private StoreItem() {
      name_ = "";
      number_ = 0;
      costType_ = "";
      costNumber_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private StoreItem(
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
            case 82: {
              java.lang.String s = input.readStringRequireUtf8();

              name_ = s;
              break;
            }
            case 88: {

              number_ = input.readUInt32();
              break;
            }
            case 98: {
              java.lang.String s = input.readStringRequireUtf8();

              costType_ = s;
              break;
            }
            case 104: {

              costNumber_ = input.readUInt32();
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
      return com.gejian.pixel.proto.StoreItemProtobuf.internal_static_StoreItem_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.StoreItemProtobuf.internal_static_StoreItem_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.StoreItemProtobuf.StoreItem.class, com.gejian.pixel.proto.StoreItemProtobuf.StoreItem.Builder.class);
    }

    public static final int NAME_FIELD_NUMBER = 10;
    private volatile java.lang.Object name_;
    /**
     * <code>string name = 10;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      }
    }
    /**
     * <code>string name = 10;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int NUMBER_FIELD_NUMBER = 11;
    private int number_;
    /**
     * <code>uint32 number = 11;</code>
     */
    public int getNumber() {
      return number_;
    }

    public static final int COST_TYPE_FIELD_NUMBER = 12;
    private volatile java.lang.Object costType_;
    /**
     * <code>string cost_type = 12;</code>
     */
    public java.lang.String getCostType() {
      java.lang.Object ref = costType_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        costType_ = s;
        return s;
      }
    }
    /**
     * <code>string cost_type = 12;</code>
     */
    public com.google.protobuf.ByteString
        getCostTypeBytes() {
      java.lang.Object ref = costType_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        costType_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int COST_NUMBER_FIELD_NUMBER = 13;
    private int costNumber_;
    /**
     * <code>uint32 cost_number = 13;</code>
     */
    public int getCostNumber() {
      return costNumber_;
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
      if (!getNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 10, name_);
      }
      if (number_ != 0) {
        output.writeUInt32(11, number_);
      }
      if (!getCostTypeBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 12, costType_);
      }
      if (costNumber_ != 0) {
        output.writeUInt32(13, costNumber_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(10, name_);
      }
      if (number_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(11, number_);
      }
      if (!getCostTypeBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(12, costType_);
      }
      if (costNumber_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(13, costNumber_);
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
      if (!(obj instanceof com.gejian.pixel.proto.StoreItemProtobuf.StoreItem)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.StoreItemProtobuf.StoreItem other = (com.gejian.pixel.proto.StoreItemProtobuf.StoreItem) obj;

      boolean result = true;
      result = result && getName()
          .equals(other.getName());
      result = result && (getNumber()
          == other.getNumber());
      result = result && getCostType()
          .equals(other.getCostType());
      result = result && (getCostNumber()
          == other.getCostNumber());
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
      hash = (37 * hash) + NAME_FIELD_NUMBER;
      hash = (53 * hash) + getName().hashCode();
      hash = (37 * hash) + NUMBER_FIELD_NUMBER;
      hash = (53 * hash) + getNumber();
      hash = (37 * hash) + COST_TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getCostType().hashCode();
      hash = (37 * hash) + COST_NUMBER_FIELD_NUMBER;
      hash = (53 * hash) + getCostNumber();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.gejian.pixel.proto.StoreItemProtobuf.StoreItem parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.StoreItemProtobuf.StoreItem parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.StoreItemProtobuf.StoreItem parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.StoreItemProtobuf.StoreItem parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.StoreItemProtobuf.StoreItem parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.StoreItemProtobuf.StoreItem parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.StoreItemProtobuf.StoreItem parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.StoreItemProtobuf.StoreItem parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.StoreItemProtobuf.StoreItem parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.StoreItemProtobuf.StoreItem parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.StoreItemProtobuf.StoreItem parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.StoreItemProtobuf.StoreItem parseFrom(
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
    public static Builder newBuilder(com.gejian.pixel.proto.StoreItemProtobuf.StoreItem prototype) {
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
     * Protobuf type {@code StoreItem}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:StoreItem)
        com.gejian.pixel.proto.StoreItemProtobuf.StoreItemOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.StoreItemProtobuf.internal_static_StoreItem_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.StoreItemProtobuf.internal_static_StoreItem_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.StoreItemProtobuf.StoreItem.class, com.gejian.pixel.proto.StoreItemProtobuf.StoreItem.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.StoreItemProtobuf.StoreItem.newBuilder()
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
        name_ = "";

        number_ = 0;

        costType_ = "";

        costNumber_ = 0;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gejian.pixel.proto.StoreItemProtobuf.internal_static_StoreItem_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.StoreItemProtobuf.StoreItem getDefaultInstanceForType() {
        return com.gejian.pixel.proto.StoreItemProtobuf.StoreItem.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.StoreItemProtobuf.StoreItem build() {
        com.gejian.pixel.proto.StoreItemProtobuf.StoreItem result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.StoreItemProtobuf.StoreItem buildPartial() {
        com.gejian.pixel.proto.StoreItemProtobuf.StoreItem result = new com.gejian.pixel.proto.StoreItemProtobuf.StoreItem(this);
        result.name_ = name_;
        result.number_ = number_;
        result.costType_ = costType_;
        result.costNumber_ = costNumber_;
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
        if (other instanceof com.gejian.pixel.proto.StoreItemProtobuf.StoreItem) {
          return mergeFrom((com.gejian.pixel.proto.StoreItemProtobuf.StoreItem)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.StoreItemProtobuf.StoreItem other) {
        if (other == com.gejian.pixel.proto.StoreItemProtobuf.StoreItem.getDefaultInstance()) return this;
        if (!other.getName().isEmpty()) {
          name_ = other.name_;
          onChanged();
        }
        if (other.getNumber() != 0) {
          setNumber(other.getNumber());
        }
        if (!other.getCostType().isEmpty()) {
          costType_ = other.costType_;
          onChanged();
        }
        if (other.getCostNumber() != 0) {
          setCostNumber(other.getCostNumber());
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
        com.gejian.pixel.proto.StoreItemProtobuf.StoreItem parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.StoreItemProtobuf.StoreItem) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object name_ = "";
      /**
       * <code>string name = 10;</code>
       */
      public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          name_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string name = 10;</code>
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string name = 10;</code>
       */
      public Builder setName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        name_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string name = 10;</code>
       */
      public Builder clearName() {
        
        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      /**
       * <code>string name = 10;</code>
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        name_ = value;
        onChanged();
        return this;
      }

      private int number_ ;
      /**
       * <code>uint32 number = 11;</code>
       */
      public int getNumber() {
        return number_;
      }
      /**
       * <code>uint32 number = 11;</code>
       */
      public Builder setNumber(int value) {
        
        number_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 number = 11;</code>
       */
      public Builder clearNumber() {
        
        number_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object costType_ = "";
      /**
       * <code>string cost_type = 12;</code>
       */
      public java.lang.String getCostType() {
        java.lang.Object ref = costType_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          costType_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string cost_type = 12;</code>
       */
      public com.google.protobuf.ByteString
          getCostTypeBytes() {
        java.lang.Object ref = costType_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          costType_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string cost_type = 12;</code>
       */
      public Builder setCostType(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        costType_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string cost_type = 12;</code>
       */
      public Builder clearCostType() {
        
        costType_ = getDefaultInstance().getCostType();
        onChanged();
        return this;
      }
      /**
       * <code>string cost_type = 12;</code>
       */
      public Builder setCostTypeBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        costType_ = value;
        onChanged();
        return this;
      }

      private int costNumber_ ;
      /**
       * <code>uint32 cost_number = 13;</code>
       */
      public int getCostNumber() {
        return costNumber_;
      }
      /**
       * <code>uint32 cost_number = 13;</code>
       */
      public Builder setCostNumber(int value) {
        
        costNumber_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 cost_number = 13;</code>
       */
      public Builder clearCostNumber() {
        
        costNumber_ = 0;
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


      // @@protoc_insertion_point(builder_scope:StoreItem)
    }

    // @@protoc_insertion_point(class_scope:StoreItem)
    private static final com.gejian.pixel.proto.StoreItemProtobuf.StoreItem DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.StoreItemProtobuf.StoreItem();
    }

    public static com.gejian.pixel.proto.StoreItemProtobuf.StoreItem getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<StoreItem>
        PARSER = new com.google.protobuf.AbstractParser<StoreItem>() {
      @java.lang.Override
      public StoreItem parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new StoreItem(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<StoreItem> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<StoreItem> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.StoreItemProtobuf.StoreItem getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_StoreItem_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_StoreItem_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017StoreItem.proto\"Q\n\tStoreItem\022\014\n\004name\030\n" +
      " \001(\t\022\016\n\006number\030\013 \001(\r\022\021\n\tcost_type\030\014 \001(\t\022" +
      "\023\n\013cost_number\030\r \001(\rB+\n\026com.gejian.pixel" +
      ".protoB\021StoreItemProtobufb\006proto3"
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
    internal_static_StoreItem_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_StoreItem_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_StoreItem_descriptor,
        new java.lang.String[] { "Name", "Number", "CostType", "CostNumber", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
