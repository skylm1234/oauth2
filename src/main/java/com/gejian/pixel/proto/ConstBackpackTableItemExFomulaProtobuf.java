// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ConstBackpackTableItemExFomula.proto

package com.gejian.pixel.proto;

public final class ConstBackpackTableItemExFomulaProtobuf {
  private ConstBackpackTableItemExFomulaProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ConstBackpackTableItemExFomulaOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ConstBackpackTableItemExFomula)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>uint32 stone = 1;</code>
     */
    int getStone();
  }
  /**
   * Protobuf type {@code ConstBackpackTableItemExFomula}
   */
  public  static final class ConstBackpackTableItemExFomula extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ConstBackpackTableItemExFomula)
      ConstBackpackTableItemExFomulaOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ConstBackpackTableItemExFomula.newBuilder() to construct.
    private ConstBackpackTableItemExFomula(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ConstBackpackTableItemExFomula() {
      stone_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ConstBackpackTableItemExFomula(
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

              stone_ = input.readUInt32();
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
      return com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.internal_static_ConstBackpackTableItemExFomula_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.internal_static_ConstBackpackTableItemExFomula_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula.class, com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula.Builder.class);
    }

    public static final int STONE_FIELD_NUMBER = 1;
    private int stone_;
    /**
     * <code>uint32 stone = 1;</code>
     */
    public int getStone() {
      return stone_;
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
      if (stone_ != 0) {
        output.writeUInt32(1, stone_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (stone_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(1, stone_);
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
      if (!(obj instanceof com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula other = (com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula) obj;

      boolean result = true;
      result = result && (getStone()
          == other.getStone());
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
      hash = (37 * hash) + STONE_FIELD_NUMBER;
      hash = (53 * hash) + getStone();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula parseFrom(
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
    public static Builder newBuilder(com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula prototype) {
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
     * Protobuf type {@code ConstBackpackTableItemExFomula}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ConstBackpackTableItemExFomula)
        com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomulaOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.internal_static_ConstBackpackTableItemExFomula_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.internal_static_ConstBackpackTableItemExFomula_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula.class, com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula.newBuilder()
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
        stone_ = 0;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.internal_static_ConstBackpackTableItemExFomula_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula getDefaultInstanceForType() {
        return com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula build() {
        com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula buildPartial() {
        com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula result = new com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula(this);
        result.stone_ = stone_;
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
        if (other instanceof com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula) {
          return mergeFrom((com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula other) {
        if (other == com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula.getDefaultInstance()) return this;
        if (other.getStone() != 0) {
          setStone(other.getStone());
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
        com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int stone_ ;
      /**
       * <code>uint32 stone = 1;</code>
       */
      public int getStone() {
        return stone_;
      }
      /**
       * <code>uint32 stone = 1;</code>
       */
      public Builder setStone(int value) {
        
        stone_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 stone = 1;</code>
       */
      public Builder clearStone() {
        
        stone_ = 0;
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


      // @@protoc_insertion_point(builder_scope:ConstBackpackTableItemExFomula)
    }

    // @@protoc_insertion_point(class_scope:ConstBackpackTableItemExFomula)
    private static final com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula();
    }

    public static com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ConstBackpackTableItemExFomula>
        PARSER = new com.google.protobuf.AbstractParser<ConstBackpackTableItemExFomula>() {
      @java.lang.Override
      public ConstBackpackTableItemExFomula parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ConstBackpackTableItemExFomula(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ConstBackpackTableItemExFomula> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ConstBackpackTableItemExFomula> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.ConstBackpackTableItemExFomulaProtobuf.ConstBackpackTableItemExFomula getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ConstBackpackTableItemExFomula_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ConstBackpackTableItemExFomula_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n$ConstBackpackTableItemExFomula.proto\"/" +
      "\n\036ConstBackpackTableItemExFomula\022\r\n\005ston" +
      "e\030\001 \001(\rB@\n\026com.gejian.pixel.protoB&Const" +
      "BackpackTableItemExFomulaProtobufb\006proto" +
      "3"
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
    internal_static_ConstBackpackTableItemExFomula_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ConstBackpackTableItemExFomula_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ConstBackpackTableItemExFomula_descriptor,
        new java.lang.String[] { "Stone", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
