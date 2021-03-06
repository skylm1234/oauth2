// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CommUpdateHeroFormationRequest.proto

package com.gejian.pixel.proto;

public final class CommUpdateHeroFormationRequestProtobuf {
  private CommUpdateHeroFormationRequestProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface CommUpdateHeroFormationRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:CommUpdateHeroFormationRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>repeated .PlayerItem formations = 10;</code>
     */
    java.util.List<com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem> 
        getFormationsList();
    /**
     * <code>repeated .PlayerItem formations = 10;</code>
     */
    com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem getFormations(int index);
    /**
     * <code>repeated .PlayerItem formations = 10;</code>
     */
    int getFormationsCount();
    /**
     * <code>repeated .PlayerItem formations = 10;</code>
     */
    java.util.List<? extends com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItemOrBuilder> 
        getFormationsOrBuilderList();
    /**
     * <code>repeated .PlayerItem formations = 10;</code>
     */
    com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItemOrBuilder getFormationsOrBuilder(
        int index);

    /**
     * <code>uint32 type = 11;</code>
     */
    int getType();
  }
  /**
   * Protobuf type {@code CommUpdateHeroFormationRequest}
   */
  public  static final class CommUpdateHeroFormationRequest extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:CommUpdateHeroFormationRequest)
      CommUpdateHeroFormationRequestOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use CommUpdateHeroFormationRequest.newBuilder() to construct.
    private CommUpdateHeroFormationRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private CommUpdateHeroFormationRequest() {
      formations_ = java.util.Collections.emptyList();
      type_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private CommUpdateHeroFormationRequest(
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
              if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
                formations_ = new java.util.ArrayList<com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem>();
                mutable_bitField0_ |= 0x00000001;
              }
              formations_.add(
                  input.readMessage(com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem.parser(), extensionRegistry));
              break;
            }
            case 88: {

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
        if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
          formations_ = java.util.Collections.unmodifiableList(formations_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.internal_static_CommUpdateHeroFormationRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.internal_static_CommUpdateHeroFormationRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest.class, com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest.Builder.class);
    }

    private int bitField0_;
    public static final int FORMATIONS_FIELD_NUMBER = 10;
    private java.util.List<com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem> formations_;
    /**
     * <code>repeated .PlayerItem formations = 10;</code>
     */
    public java.util.List<com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem> getFormationsList() {
      return formations_;
    }
    /**
     * <code>repeated .PlayerItem formations = 10;</code>
     */
    public java.util.List<? extends com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItemOrBuilder> 
        getFormationsOrBuilderList() {
      return formations_;
    }
    /**
     * <code>repeated .PlayerItem formations = 10;</code>
     */
    public int getFormationsCount() {
      return formations_.size();
    }
    /**
     * <code>repeated .PlayerItem formations = 10;</code>
     */
    public com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem getFormations(int index) {
      return formations_.get(index);
    }
    /**
     * <code>repeated .PlayerItem formations = 10;</code>
     */
    public com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItemOrBuilder getFormationsOrBuilder(
        int index) {
      return formations_.get(index);
    }

    public static final int TYPE_FIELD_NUMBER = 11;
    private int type_;
    /**
     * <code>uint32 type = 11;</code>
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
      for (int i = 0; i < formations_.size(); i++) {
        output.writeMessage(10, formations_.get(i));
      }
      if (type_ != 0) {
        output.writeUInt32(11, type_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      for (int i = 0; i < formations_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(10, formations_.get(i));
      }
      if (type_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(11, type_);
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
      if (!(obj instanceof com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest other = (com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest) obj;

      boolean result = true;
      result = result && getFormationsList()
          .equals(other.getFormationsList());
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
      if (getFormationsCount() > 0) {
        hash = (37 * hash) + FORMATIONS_FIELD_NUMBER;
        hash = (53 * hash) + getFormationsList().hashCode();
      }
      hash = (37 * hash) + TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getType();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest parseFrom(
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
    public static Builder newBuilder(com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest prototype) {
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
     * Protobuf type {@code CommUpdateHeroFormationRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:CommUpdateHeroFormationRequest)
        com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.internal_static_CommUpdateHeroFormationRequest_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.internal_static_CommUpdateHeroFormationRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest.class, com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest.newBuilder()
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
          getFormationsFieldBuilder();
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        if (formationsBuilder_ == null) {
          formations_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          formationsBuilder_.clear();
        }
        type_ = 0;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.internal_static_CommUpdateHeroFormationRequest_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest getDefaultInstanceForType() {
        return com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest build() {
        com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest buildPartial() {
        com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest result = new com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (formationsBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001)) {
            formations_ = java.util.Collections.unmodifiableList(formations_);
            bitField0_ = (bitField0_ & ~0x00000001);
          }
          result.formations_ = formations_;
        } else {
          result.formations_ = formationsBuilder_.build();
        }
        result.type_ = type_;
        result.bitField0_ = to_bitField0_;
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
        if (other instanceof com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest) {
          return mergeFrom((com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest other) {
        if (other == com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest.getDefaultInstance()) return this;
        if (formationsBuilder_ == null) {
          if (!other.formations_.isEmpty()) {
            if (formations_.isEmpty()) {
              formations_ = other.formations_;
              bitField0_ = (bitField0_ & ~0x00000001);
            } else {
              ensureFormationsIsMutable();
              formations_.addAll(other.formations_);
            }
            onChanged();
          }
        } else {
          if (!other.formations_.isEmpty()) {
            if (formationsBuilder_.isEmpty()) {
              formationsBuilder_.dispose();
              formationsBuilder_ = null;
              formations_ = other.formations_;
              bitField0_ = (bitField0_ & ~0x00000001);
              formationsBuilder_ = 
                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                   getFormationsFieldBuilder() : null;
            } else {
              formationsBuilder_.addAllMessages(other.formations_);
            }
          }
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
        com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.util.List<com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem> formations_ =
        java.util.Collections.emptyList();
      private void ensureFormationsIsMutable() {
        if (!((bitField0_ & 0x00000001) == 0x00000001)) {
          formations_ = new java.util.ArrayList<com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem>(formations_);
          bitField0_ |= 0x00000001;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilderV3<
          com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem, com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem.Builder, com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItemOrBuilder> formationsBuilder_;

      /**
       * <code>repeated .PlayerItem formations = 10;</code>
       */
      public java.util.List<com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem> getFormationsList() {
        if (formationsBuilder_ == null) {
          return java.util.Collections.unmodifiableList(formations_);
        } else {
          return formationsBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .PlayerItem formations = 10;</code>
       */
      public int getFormationsCount() {
        if (formationsBuilder_ == null) {
          return formations_.size();
        } else {
          return formationsBuilder_.getCount();
        }
      }
      /**
       * <code>repeated .PlayerItem formations = 10;</code>
       */
      public com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem getFormations(int index) {
        if (formationsBuilder_ == null) {
          return formations_.get(index);
        } else {
          return formationsBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .PlayerItem formations = 10;</code>
       */
      public Builder setFormations(
          int index, com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem value) {
        if (formationsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureFormationsIsMutable();
          formations_.set(index, value);
          onChanged();
        } else {
          formationsBuilder_.setMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .PlayerItem formations = 10;</code>
       */
      public Builder setFormations(
          int index, com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem.Builder builderForValue) {
        if (formationsBuilder_ == null) {
          ensureFormationsIsMutable();
          formations_.set(index, builderForValue.build());
          onChanged();
        } else {
          formationsBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .PlayerItem formations = 10;</code>
       */
      public Builder addFormations(com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem value) {
        if (formationsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureFormationsIsMutable();
          formations_.add(value);
          onChanged();
        } else {
          formationsBuilder_.addMessage(value);
        }
        return this;
      }
      /**
       * <code>repeated .PlayerItem formations = 10;</code>
       */
      public Builder addFormations(
          int index, com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem value) {
        if (formationsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureFormationsIsMutable();
          formations_.add(index, value);
          onChanged();
        } else {
          formationsBuilder_.addMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .PlayerItem formations = 10;</code>
       */
      public Builder addFormations(
          com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem.Builder builderForValue) {
        if (formationsBuilder_ == null) {
          ensureFormationsIsMutable();
          formations_.add(builderForValue.build());
          onChanged();
        } else {
          formationsBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .PlayerItem formations = 10;</code>
       */
      public Builder addFormations(
          int index, com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem.Builder builderForValue) {
        if (formationsBuilder_ == null) {
          ensureFormationsIsMutable();
          formations_.add(index, builderForValue.build());
          onChanged();
        } else {
          formationsBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .PlayerItem formations = 10;</code>
       */
      public Builder addAllFormations(
          java.lang.Iterable<? extends com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem> values) {
        if (formationsBuilder_ == null) {
          ensureFormationsIsMutable();
          com.google.protobuf.AbstractMessageLite.Builder.addAll(
              values, formations_);
          onChanged();
        } else {
          formationsBuilder_.addAllMessages(values);
        }
        return this;
      }
      /**
       * <code>repeated .PlayerItem formations = 10;</code>
       */
      public Builder clearFormations() {
        if (formationsBuilder_ == null) {
          formations_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
          onChanged();
        } else {
          formationsBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .PlayerItem formations = 10;</code>
       */
      public Builder removeFormations(int index) {
        if (formationsBuilder_ == null) {
          ensureFormationsIsMutable();
          formations_.remove(index);
          onChanged();
        } else {
          formationsBuilder_.remove(index);
        }
        return this;
      }
      /**
       * <code>repeated .PlayerItem formations = 10;</code>
       */
      public com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem.Builder getFormationsBuilder(
          int index) {
        return getFormationsFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .PlayerItem formations = 10;</code>
       */
      public com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItemOrBuilder getFormationsOrBuilder(
          int index) {
        if (formationsBuilder_ == null) {
          return formations_.get(index);  } else {
          return formationsBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .PlayerItem formations = 10;</code>
       */
      public java.util.List<? extends com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItemOrBuilder> 
           getFormationsOrBuilderList() {
        if (formationsBuilder_ != null) {
          return formationsBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(formations_);
        }
      }
      /**
       * <code>repeated .PlayerItem formations = 10;</code>
       */
      public com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem.Builder addFormationsBuilder() {
        return getFormationsFieldBuilder().addBuilder(
            com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem.getDefaultInstance());
      }
      /**
       * <code>repeated .PlayerItem formations = 10;</code>
       */
      public com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem.Builder addFormationsBuilder(
          int index) {
        return getFormationsFieldBuilder().addBuilder(
            index, com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem.getDefaultInstance());
      }
      /**
       * <code>repeated .PlayerItem formations = 10;</code>
       */
      public java.util.List<com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem.Builder> 
           getFormationsBuilderList() {
        return getFormationsFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilderV3<
          com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem, com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem.Builder, com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItemOrBuilder> 
          getFormationsFieldBuilder() {
        if (formationsBuilder_ == null) {
          formationsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
              com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem, com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItem.Builder, com.gejian.pixel.proto.PlayerItemProtobuf.PlayerItemOrBuilder>(
                  formations_,
                  ((bitField0_ & 0x00000001) == 0x00000001),
                  getParentForChildren(),
                  isClean());
          formations_ = null;
        }
        return formationsBuilder_;
      }

      private int type_ ;
      /**
       * <code>uint32 type = 11;</code>
       */
      public int getType() {
        return type_;
      }
      /**
       * <code>uint32 type = 11;</code>
       */
      public Builder setType(int value) {
        
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 type = 11;</code>
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


      // @@protoc_insertion_point(builder_scope:CommUpdateHeroFormationRequest)
    }

    // @@protoc_insertion_point(class_scope:CommUpdateHeroFormationRequest)
    private static final com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest();
    }

    public static com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<CommUpdateHeroFormationRequest>
        PARSER = new com.google.protobuf.AbstractParser<CommUpdateHeroFormationRequest>() {
      @java.lang.Override
      public CommUpdateHeroFormationRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new CommUpdateHeroFormationRequest(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<CommUpdateHeroFormationRequest> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<CommUpdateHeroFormationRequest> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CommUpdateHeroFormationRequest_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CommUpdateHeroFormationRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n$CommUpdateHeroFormationRequest.proto\032\020" +
      "PlayerItem.proto\"O\n\036CommUpdateHeroFormat" +
      "ionRequest\022\037\n\nformations\030\n \003(\0132\013.PlayerI" +
      "tem\022\014\n\004type\030\013 \001(\rB@\n\026com.gejian.pixel.pr" +
      "otoB&CommUpdateHeroFormationRequestProto" +
      "bufb\006proto3"
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
          com.gejian.pixel.proto.PlayerItemProtobuf.getDescriptor(),
        }, assigner);
    internal_static_CommUpdateHeroFormationRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_CommUpdateHeroFormationRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommUpdateHeroFormationRequest_descriptor,
        new java.lang.String[] { "Formations", "Type", });
    com.gejian.pixel.proto.PlayerItemProtobuf.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
