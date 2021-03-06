// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CommDismissHeroRequest.proto

package com.gejian.pixel.proto;

public final class CommDismissHeroRequestProtobuf {
  private CommDismissHeroRequestProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface CommDismissHeroRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:CommDismissHeroRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>repeated string heros = 10;</code>
     */
    java.util.List<java.lang.String>
        getHerosList();
    /**
     * <code>repeated string heros = 10;</code>
     */
    int getHerosCount();
    /**
     * <code>repeated string heros = 10;</code>
     */
    java.lang.String getHeros(int index);
    /**
     * <code>repeated string heros = 10;</code>
     */
    com.google.protobuf.ByteString
        getHerosBytes(int index);
  }
  /**
   * Protobuf type {@code CommDismissHeroRequest}
   */
  public  static final class CommDismissHeroRequest extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:CommDismissHeroRequest)
      CommDismissHeroRequestOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use CommDismissHeroRequest.newBuilder() to construct.
    private CommDismissHeroRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private CommDismissHeroRequest() {
      heros_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private CommDismissHeroRequest(
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
              if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
                heros_ = new com.google.protobuf.LazyStringArrayList();
                mutable_bitField0_ |= 0x00000001;
              }
              heros_.add(s);
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
          heros_ = heros_.getUnmodifiableView();
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.internal_static_CommDismissHeroRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.internal_static_CommDismissHeroRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest.class, com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest.Builder.class);
    }

    public static final int HEROS_FIELD_NUMBER = 10;
    private com.google.protobuf.LazyStringList heros_;
    /**
     * <code>repeated string heros = 10;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getHerosList() {
      return heros_;
    }
    /**
     * <code>repeated string heros = 10;</code>
     */
    public int getHerosCount() {
      return heros_.size();
    }
    /**
     * <code>repeated string heros = 10;</code>
     */
    public java.lang.String getHeros(int index) {
      return heros_.get(index);
    }
    /**
     * <code>repeated string heros = 10;</code>
     */
    public com.google.protobuf.ByteString
        getHerosBytes(int index) {
      return heros_.getByteString(index);
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
      for (int i = 0; i < heros_.size(); i++) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 10, heros_.getRaw(i));
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      {
        int dataSize = 0;
        for (int i = 0; i < heros_.size(); i++) {
          dataSize += computeStringSizeNoTag(heros_.getRaw(i));
        }
        size += dataSize;
        size += 1 * getHerosList().size();
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
      if (!(obj instanceof com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest other = (com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest) obj;

      boolean result = true;
      result = result && getHerosList()
          .equals(other.getHerosList());
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
      if (getHerosCount() > 0) {
        hash = (37 * hash) + HEROS_FIELD_NUMBER;
        hash = (53 * hash) + getHerosList().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest parseFrom(
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
    public static Builder newBuilder(com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest prototype) {
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
     * Protobuf type {@code CommDismissHeroRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:CommDismissHeroRequest)
        com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.internal_static_CommDismissHeroRequest_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.internal_static_CommDismissHeroRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest.class, com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest.newBuilder()
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
        heros_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.internal_static_CommDismissHeroRequest_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest getDefaultInstanceForType() {
        return com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest build() {
        com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest buildPartial() {
        com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest result = new com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest(this);
        int from_bitField0_ = bitField0_;
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          heros_ = heros_.getUnmodifiableView();
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.heros_ = heros_;
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
        if (other instanceof com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest) {
          return mergeFrom((com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest other) {
        if (other == com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest.getDefaultInstance()) return this;
        if (!other.heros_.isEmpty()) {
          if (heros_.isEmpty()) {
            heros_ = other.heros_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureHerosIsMutable();
            heros_.addAll(other.heros_);
          }
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
        com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private com.google.protobuf.LazyStringList heros_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      private void ensureHerosIsMutable() {
        if (!((bitField0_ & 0x00000001) == 0x00000001)) {
          heros_ = new com.google.protobuf.LazyStringArrayList(heros_);
          bitField0_ |= 0x00000001;
         }
      }
      /**
       * <code>repeated string heros = 10;</code>
       */
      public com.google.protobuf.ProtocolStringList
          getHerosList() {
        return heros_.getUnmodifiableView();
      }
      /**
       * <code>repeated string heros = 10;</code>
       */
      public int getHerosCount() {
        return heros_.size();
      }
      /**
       * <code>repeated string heros = 10;</code>
       */
      public java.lang.String getHeros(int index) {
        return heros_.get(index);
      }
      /**
       * <code>repeated string heros = 10;</code>
       */
      public com.google.protobuf.ByteString
          getHerosBytes(int index) {
        return heros_.getByteString(index);
      }
      /**
       * <code>repeated string heros = 10;</code>
       */
      public Builder setHeros(
          int index, java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureHerosIsMutable();
        heros_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string heros = 10;</code>
       */
      public Builder addHeros(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureHerosIsMutable();
        heros_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string heros = 10;</code>
       */
      public Builder addAllHeros(
          java.lang.Iterable<java.lang.String> values) {
        ensureHerosIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, heros_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string heros = 10;</code>
       */
      public Builder clearHeros() {
        heros_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string heros = 10;</code>
       */
      public Builder addHerosBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        ensureHerosIsMutable();
        heros_.add(value);
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


      // @@protoc_insertion_point(builder_scope:CommDismissHeroRequest)
    }

    // @@protoc_insertion_point(class_scope:CommDismissHeroRequest)
    private static final com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest();
    }

    public static com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<CommDismissHeroRequest>
        PARSER = new com.google.protobuf.AbstractParser<CommDismissHeroRequest>() {
      @java.lang.Override
      public CommDismissHeroRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new CommDismissHeroRequest(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<CommDismissHeroRequest> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<CommDismissHeroRequest> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.CommDismissHeroRequestProtobuf.CommDismissHeroRequest getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CommDismissHeroRequest_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CommDismissHeroRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\034CommDismissHeroRequest.proto\"\'\n\026CommDi" +
      "smissHeroRequest\022\r\n\005heros\030\n \003(\tB8\n\026com.g" +
      "ejian.pixel.protoB\036CommDismissHeroReques" +
      "tProtobufb\006proto3"
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
    internal_static_CommDismissHeroRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_CommDismissHeroRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommDismissHeroRequest_descriptor,
        new java.lang.String[] { "Heros", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
