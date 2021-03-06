// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CommUpgradeTemporaryBackpackRequest.proto

package com.gejian.pixel.proto;

public final class CommUpgradeTemporaryBackpackRequestProtobuf {
  private CommUpgradeTemporaryBackpackRequestProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface CommUpgradeTemporaryBackpackRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:CommUpgradeTemporaryBackpackRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>uint32 dummy = 10;</code>
     */
    int getDummy();
  }
  /**
   * Protobuf type {@code CommUpgradeTemporaryBackpackRequest}
   */
  public  static final class CommUpgradeTemporaryBackpackRequest extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:CommUpgradeTemporaryBackpackRequest)
      CommUpgradeTemporaryBackpackRequestOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use CommUpgradeTemporaryBackpackRequest.newBuilder() to construct.
    private CommUpgradeTemporaryBackpackRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private CommUpgradeTemporaryBackpackRequest() {
      dummy_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private CommUpgradeTemporaryBackpackRequest(
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
      return com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.internal_static_CommUpgradeTemporaryBackpackRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.internal_static_CommUpgradeTemporaryBackpackRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest.class, com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest.Builder.class);
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
      if (!(obj instanceof com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest other = (com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest) obj;

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

    public static com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest parseFrom(
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
    public static Builder newBuilder(com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest prototype) {
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
     * Protobuf type {@code CommUpgradeTemporaryBackpackRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:CommUpgradeTemporaryBackpackRequest)
        com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.internal_static_CommUpgradeTemporaryBackpackRequest_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.internal_static_CommUpgradeTemporaryBackpackRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest.class, com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest.newBuilder()
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
        return com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.internal_static_CommUpgradeTemporaryBackpackRequest_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest getDefaultInstanceForType() {
        return com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest build() {
        com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest buildPartial() {
        com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest result = new com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest(this);
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
        if (other instanceof com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest) {
          return mergeFrom((com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest other) {
        if (other == com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest.getDefaultInstance()) return this;
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
        com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest) e.getUnfinishedMessage();
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


      // @@protoc_insertion_point(builder_scope:CommUpgradeTemporaryBackpackRequest)
    }

    // @@protoc_insertion_point(class_scope:CommUpgradeTemporaryBackpackRequest)
    private static final com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest();
    }

    public static com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<CommUpgradeTemporaryBackpackRequest>
        PARSER = new com.google.protobuf.AbstractParser<CommUpgradeTemporaryBackpackRequest>() {
      @java.lang.Override
      public CommUpgradeTemporaryBackpackRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new CommUpgradeTemporaryBackpackRequest(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<CommUpgradeTemporaryBackpackRequest> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<CommUpgradeTemporaryBackpackRequest> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CommUpgradeTemporaryBackpackRequest_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CommUpgradeTemporaryBackpackRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n)CommUpgradeTemporaryBackpackRequest.pr" +
      "oto\"4\n#CommUpgradeTemporaryBackpackReque" +
      "st\022\r\n\005dummy\030\n \001(\rBE\n\026com.gejian.pixel.pr" +
      "otoB+CommUpgradeTemporaryBackpackRequest" +
      "Protobufb\006proto3"
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
    internal_static_CommUpgradeTemporaryBackpackRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_CommUpgradeTemporaryBackpackRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommUpgradeTemporaryBackpackRequest_descriptor,
        new java.lang.String[] { "Dummy", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
