// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CommQueryBuyHeroPriceRequest.proto

package com.gejian.pixel.proto;

public final class CommQueryBuyHeroPriceRequestProtobuf {
  private CommQueryBuyHeroPriceRequestProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface CommQueryBuyHeroPriceRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:CommQueryBuyHeroPriceRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>uint32 dummy = 10;</code>
     */
    int getDummy();
  }
  /**
   * Protobuf type {@code CommQueryBuyHeroPriceRequest}
   */
  public  static final class CommQueryBuyHeroPriceRequest extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:CommQueryBuyHeroPriceRequest)
      CommQueryBuyHeroPriceRequestOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use CommQueryBuyHeroPriceRequest.newBuilder() to construct.
    private CommQueryBuyHeroPriceRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private CommQueryBuyHeroPriceRequest() {
      dummy_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private CommQueryBuyHeroPriceRequest(
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

              dummy_ = input.readUInt32();
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
      return com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.internal_static_CommQueryBuyHeroPriceRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.internal_static_CommQueryBuyHeroPriceRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest.class, com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest.Builder.class);
    }

    public static final int DUMMY_FIELD_NUMBER = 10;
    private int dummy_;
    /**
     * <code>uint32 dummy = 10;</code>
     */
    public int getDummy() {
      return dummy_;
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
      if (dummy_ != 0) {
        output.writeUInt32(10, dummy_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (dummy_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(10, dummy_);
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
      if (!(obj instanceof com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest other = (com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest) obj;

      boolean result = true;
      result = result && (getDummy()
          == other.getDummy());
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
      hash = (37 * hash) + DUMMY_FIELD_NUMBER;
      hash = (53 * hash) + getDummy();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest parseFrom(
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
    public static Builder newBuilder(com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest prototype) {
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
     * Protobuf type {@code CommQueryBuyHeroPriceRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:CommQueryBuyHeroPriceRequest)
        com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.internal_static_CommQueryBuyHeroPriceRequest_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.internal_static_CommQueryBuyHeroPriceRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest.class, com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest.newBuilder()
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
        dummy_ = 0;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.internal_static_CommQueryBuyHeroPriceRequest_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest getDefaultInstanceForType() {
        return com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest build() {
        com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest buildPartial() {
        com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest result = new com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest(this);
        result.dummy_ = dummy_;
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
        if (other instanceof com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest) {
          return mergeFrom((com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest other) {
        if (other == com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest.getDefaultInstance()) return this;
        if (other.getDummy() != 0) {
          setDummy(other.getDummy());
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
        com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int dummy_ ;
      /**
       * <code>uint32 dummy = 10;</code>
       */
      public int getDummy() {
        return dummy_;
      }
      /**
       * <code>uint32 dummy = 10;</code>
       */
      public Builder setDummy(int value) {
        
        dummy_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 dummy = 10;</code>
       */
      public Builder clearDummy() {
        
        dummy_ = 0;
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


      // @@protoc_insertion_point(builder_scope:CommQueryBuyHeroPriceRequest)
    }

    // @@protoc_insertion_point(class_scope:CommQueryBuyHeroPriceRequest)
    private static final com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest();
    }

    public static com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<CommQueryBuyHeroPriceRequest>
        PARSER = new com.google.protobuf.AbstractParser<CommQueryBuyHeroPriceRequest>() {
      @java.lang.Override
      public CommQueryBuyHeroPriceRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new CommQueryBuyHeroPriceRequest(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<CommQueryBuyHeroPriceRequest> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<CommQueryBuyHeroPriceRequest> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CommQueryBuyHeroPriceRequest_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CommQueryBuyHeroPriceRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\"CommQueryBuyHeroPriceRequest.proto\"-\n\034" +
      "CommQueryBuyHeroPriceRequest\022\r\n\005dummy\030\n " +
      "\001(\rB>\n\026com.gejian.pixel.protoB$CommQuery" +
      "BuyHeroPriceRequestProtobufb\006proto3"
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
    internal_static_CommQueryBuyHeroPriceRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_CommQueryBuyHeroPriceRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommQueryBuyHeroPriceRequest_descriptor,
        new java.lang.String[] { "Dummy", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
